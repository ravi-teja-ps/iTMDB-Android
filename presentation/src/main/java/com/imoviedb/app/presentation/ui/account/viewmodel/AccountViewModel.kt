package com.imoviedb.app.presentation.ui.account.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.account.model.AccountDomainModel
import com.imoviedb.app.domain.account.usecase.GetAccountUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.GetUserSessionUseCase
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.common.state.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: GetAccountUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase
) : BaseViewModel() {

    private val _dataUiState: MutableStateFlow<UiState<AccountDomainModel>> = MutableStateFlow(
        UiState.Loading(
            true
        )
    )
    var uiState = _dataUiState

    fun getAccountData() {
        viewModelScope.launch {
            _dataUiState.value = UiState.Loading(true)
            getUserSessionUseCase.getUserSession().collect { sessionId ->
                accountUseCase.getAccountInfo(sessionId).collect {
                    when(it){
                        is ResponseWrapper.Error ->  {
                            uiState.value = UiState.OnError(it.code,it.message)
                        }

                        is  ResponseWrapper.Success ->  {
                            uiState.value = UiState.OnComplete(it.data)
                        }
                    }
                }
            }
         }
    }
}
