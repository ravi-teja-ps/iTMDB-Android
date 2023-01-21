package com.imoviedb.app.presentation.ui.authentication.viewmodel


import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCase
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.utils.KeyUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * login screen view model to execute a series of steps to authenticate user
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val createNewSessionUseCase: CreateNewSessionUseCase,
    private val guestTokenUseCase: AuthenticationUseCase
) : BaseViewModel() {

    //Ui status of login screen
    private val _loginScreenUiState = MutableStateFlow<State>(State.Loading(false))
    val loginScreenUiState = _loginScreenUiState

    //user name state
    private val _userName = MutableStateFlow("")
    val userName = _userName

    //pwd state
    private val _password = MutableStateFlow("")
    val password = _password

    //Function to check if sign in button can be enabled or not
    private val _signInButtonStatus: Flow<Boolean> =
        combine(_userName, _password) { userID, password ->
            val regexString = "\\w+"
            val userIdCorrect = userID.matches(regexString.toRegex())
            val isPasswordCorrect = password.isNotEmpty()
            return@combine userIdCorrect and isPasswordCorrect
        }

    val signInButtonStatus = _signInButtonStatus

    //still refine this using lambda onComplete onError to handle errors in a single place
    fun login() {
        _loginScreenUiState.value = State.Loading(true)
        viewModelScope.launch {
            //Step 0   get access token
            guestTokenUseCase.createTokenForSession().collect { savedUserTokenModel ->
                //Step 1  authenticate user name password of user for right accounts only
                if (savedUserTokenModel.isResponseSuccessful()) {
                    savedUserTokenModel.requestToken?.let {
                        //Step 2 validate the received access_token for a new session_id and save it across
                        val authenticationBody =
                            AuthenticationBody(userName.value, password.value, it)
                        validateUserCredential(authenticationBody)
                    }
                } else {
                    _loginScreenUiState.value = State.OnError(
                        savedUserTokenModel.statusCode,
                        savedUserTokenModel.statusMessage
                    )
                    _loginScreenUiState.value = State.Loading(false)
                }
            }
        }
    }

    /**
     * validate a user credentials if api is asking for a encoding then use base64
     */
    private suspend fun validateUserCredential(authenticationBody: AuthenticationBody) {
        loginUserUseCase.validateUserCredential(authenticationBody)
            .collect { accessTokenModel ->
                if (accessTokenModel.isResponseSuccessful()) {
                    accessTokenModel.requestToken.let {
                        //Step3 use the sessionId and get a account ID and account data for future
                        val accessTokenAsMapBody = HashMap<String, String>().apply {
                            put(KeyUtils.REQUEST_TOKEN_KEY, it)
                        }
                        createNewSessionPostAuthentication(accessTokenAsMapBody)
                    }
                } else {
                    _loginScreenUiState.value =
                        State.OnError(accessTokenModel.statusCode, accessTokenModel.statusMessage)
                    _loginScreenUiState.value = State.Loading(false)
                }
            }
    }

    /**
     * Create a session using the obtained access token here
     */
    private suspend fun createNewSessionPostAuthentication(accessTokenAsMapBody: HashMap<String, String>) {
        createNewSessionUseCase.createNewSession(accessTokenAsMapBody)
            .collect { newSessionModel ->
                if (newSessionModel.isResponseSuccessful()) {
                    _loginScreenUiState.value = State.OnComplete(newSessionModel)

                } else {
                    _loginScreenUiState.value =
                        State.OnError(newSessionModel.statusCode, newSessionModel.statusMessage)
                }
            }
        _loginScreenUiState.value = State.Loading(false)
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setUserId(id: String) {
        _userName.value = id
    }
}