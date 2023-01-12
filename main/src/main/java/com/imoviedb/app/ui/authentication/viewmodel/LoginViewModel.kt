package com.imoviedb.app.ui.authentication.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.data.networking.utils.DispatcherProvider
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCase
import com.imoviedb.app.ui.core.BaseViewModel
import com.imoviedb.app.ui.core.BaseViewModel.State.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * login screen view model to execute a series of steps to authenticate user
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val createNewSessionUseCase: CreateNewSessionUseCase,
    private val guestTokenUseCase: AuthenticationUseCase,
    private val coroutineDispatcher: DispatcherProvider
) : BaseViewModel() {

    private val _loginStatus = MutableStateFlow<State>(Loading(false))
    val loginStatus = _loginStatus

    //still refine this using lambda onComplete onError to handle errors in a single place
    fun login(userName: String, password: String) {
        _loginStatus.value = Loading(true)
        viewModelScope.launch {
            //Step 0   get access token
            guestTokenUseCase.createTokenForSession(coroutineDispatcher.io)
                .flowOn(Dispatchers.Default).collect { savedUserTokenModel ->
                //Step 1  authenticate user name password of user for right accounts only
                if (savedUserTokenModel.success == true) {
                    savedUserTokenModel.request_token?.let {
                        //Step 2 validate the received access_token for a new session_id and save it across
                        val authenticationBody = AuthenticationBody(userName, password, it)
                        validateUserCredential(authenticationBody)
                    }
                } else {
                    _loginStatus.value =OnError(savedUserTokenModel.statusCode)
                }
            }
        }
    }

    /**
     * validate a user credentials if api is asking for a encoding then use base64
     */
    private suspend fun validateUserCredential(authenticationBody: AuthenticationBody) {
        loginUserUseCase.validateUserCredential(authenticationBody).collect { accessTokenModel ->
            if (accessTokenModel.success == true) {
                accessTokenModel.requestToken?.let {
                    //Step3 use the sessionId and get a account ID and account data for future
                    val accessTokenAsMapBody = HashMap<String, String>().apply {
                        put("request_token", it)
                    }
                    createNewSessionPostAuthentication(accessTokenAsMapBody)
                }
            } else {
                _loginStatus.value = OnError(accessTokenModel.statusCode)
            }
        }
    }

    /**
     * Create a session using the obtained access token here
     */
    private suspend fun createNewSessionPostAuthentication(accessTokenAsMapBody: HashMap<String, String>) {
        createNewSessionUseCase.createNewSession(accessTokenAsMapBody).collect { newSessionModel ->
            if (newSessionModel.success == true && newSessionModel.sessionId != null) {
                _loginStatus.value = Loading(false)
                _loginStatus.value = OnComplete(newSessionModel)

            } else {
                _loginStatus.value = OnError(newSessionModel.statusCode)
            }
        }
    }
}