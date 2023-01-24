package com.imoviedb.app.presentation.ui.authentication.viewmodel


import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCase
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.app.presentation.ui.utils.KeyUtils
import com.imoviedb.common.state.ResponseWrapper
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
    private val _loginScreenUiUiState = MutableStateFlow<UiState<NewSessionDomainModel>>(UiState.Loading(false))
    val loginScreenUiState = _loginScreenUiUiState

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
        _loginScreenUiUiState.value = UiState.Loading(true)
        viewModelScope.launch {
            //Step 0   get access token
            guestTokenUseCase.createTokenForSession().collect { result ->

                when(result){
                    is ResponseWrapper.Error -> {
                        _loginScreenUiUiState.value =
                            UiState.OnError(result.code, result.message)
                    }

                    is ResponseWrapper.Success -> {
                        result.data.requestToken?.let {
                            //Step 2 validate the received access_token for a new session_id and save it across
                            val authenticationBody =
                                AuthenticationBody(userName.value, password.value, it)
                            validateUserCredential(authenticationBody)
                        }
                    }
                }
            }
        }
    }

    /**
     * validate a user credentials if api is asking for a encoding then use base64
     */
    private suspend fun validateUserCredential(authenticationBody: AuthenticationBody) {
        loginUserUseCase.validateUserCredential(authenticationBody)
            .collect {
                when(it){
                    is ResponseWrapper.Error ->{
                        _loginScreenUiUiState.value =
                            UiState.OnError(it.code, it.message)
                    }

                    is ResponseWrapper.Success ->  {
                        //Step3 use the sessionId and get a account ID and account data for future
                        val accessTokenAsMapBody = HashMap<String, String>().apply {
                            put(KeyUtils.REQUEST_TOKEN_KEY, it.data.requestToken)
                        }
                        createNewSessionPostAuthentication(accessTokenAsMapBody)
                    }
                }
            }
    }

    /**
     * Create a session using the obtained access token here
     */
    private suspend fun createNewSessionPostAuthentication(accessTokenAsMapBody: HashMap<String, String>) {
        createNewSessionUseCase.createNewSession(accessTokenAsMapBody)
            .collect { result ->

                when(result){
                    is ResponseWrapper.Error ->  { _loginScreenUiUiState.value =
                        UiState.OnError(result.code, result.message)}

                    is ResponseWrapper.Success ->{
                        _loginScreenUiUiState.value = UiState.OnComplete(result.data)
                    }
                }
            }
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setUserId(id: String) {
        _userName.value = id
    }
}