package com.imoviedb.app.ui.startup.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.data.networking.utils.DispatcherProvider
import com.imoviedb.app.data.networking.utils.ErrorCodes
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.ui.core.BaseViewModel
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

    private val _dataState: MutableStateFlow<State> = MutableStateFlow(State.Loading(true))
    var splashScreenState = _dataState
        private set

    /** Load access token from service and use it for subsequent calls **/
    fun loadAccessTokenWithoutSession() {
        viewModelScope.launch {
            _dataState.value = State.Loading(true)
            authenticationUseCase.deleteGuestToken(coroutineDispatcher.io)
            authenticationUseCase.createTokenForSession(coroutineDispatcher.io).catch {
                _dataState.value = State.OnError(ErrorCodes.INTERNAL)
            }.collect {
                _dataState.value = State.OnComplete(it)
            }
        }
    }
}