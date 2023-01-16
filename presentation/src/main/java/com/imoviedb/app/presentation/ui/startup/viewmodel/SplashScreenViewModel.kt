package com.imoviedb.app.presentation.ui.startup.viewmodel

import androidx.lifecycle.viewModelScope

import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.utils.ErrorCodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val coroutineDispatcher: DispatcherProvider
) : BaseViewModel() {

    private val _dataState: MutableStateFlow<State> = MutableStateFlow(
        State.Loading(
            true
        )
    )
    var splashScreenState = _dataState
        private set

    /** Load access token from service and use it for subsequent calls **/
    fun loadAccessTokenWithoutSession() {
        viewModelScope.launch {
            _dataState.value = State.Loading(true)
            authenticationUseCase.deleteGuestToken(coroutineDispatcher.io)
            authenticationUseCase.createTokenForSession(coroutineDispatcher.io).collect {
                if(it.success == true){
                    _dataState.value = State.OnComplete(it)
                }else{
                    _dataState.value = State.OnError(it.statusCode, it.statusMessage)
                }
            }
            _dataState.value = State.Loading(false)
        }
    }
}