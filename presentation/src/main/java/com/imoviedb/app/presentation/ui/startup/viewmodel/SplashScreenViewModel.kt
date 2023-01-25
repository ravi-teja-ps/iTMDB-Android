package com.imoviedb.app.presentation.ui.startup.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.guestuser.usecase.DeleteGuestTokenUseCase
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.common.state.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val deleteGuestTokenUseCase: DeleteGuestTokenUseCase
) : BaseViewModel() {

    private val _dataUiState: MutableStateFlow<UiState<GuestAuthCreateTokenDomainModel>> = MutableStateFlow(
        UiState.Loading(
            true
        )
    )
    var splashScreenState = _dataUiState
        private set

    /** Load access token from service and use it for subsequent calls **/
    fun loadAccessTokenWithoutSession() {
        viewModelScope.launch {
            _dataUiState.value = UiState.Loading(true)
            //delete previous sessions as a measure
            deleteGuestTokenUseCase.deleteGuestToken()
            authenticationUseCase.createTokenForSession().collect {
             when(it){
                 is ResponseWrapper.Error ->  {
                     _dataUiState.value = UiState.OnError(it.code,it.message)
                 }

                 is ResponseWrapper.Success ->{
                     _dataUiState.value = UiState.OnComplete(it.data)

                 }
             }
            }
        }
    }
}