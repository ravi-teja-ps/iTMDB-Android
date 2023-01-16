package com.imoviedb.app.presentation.ui.account.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.account.usecase.GetAccountUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.GetUserSessionUseCase
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountUseCase: GetAccountUseCase,
                                           private val getUserSessionUseCase: GetUserSessionUseCase
) : BaseViewModel() {

    private val _dataState : MutableStateFlow<State> = MutableStateFlow(
        State.Loading(
            true
        )
    )
    var dataState = _dataState

    fun getAccountData( ){
        viewModelScope.launch {
            _dataState.value = State.Loading(true)
            getUserSessionUseCase.getUserSession().collect{ sessionId->
                accountUseCase.getAccountInfo(sessionId).collect{
                    if(it.isSuccess()){
                        _dataState.value = State.OnComplete(it)
                    }else{
                        _dataState.value = State.OnError(it.statusCode,it.statusMessage)
                    }
                }
            }
            _dataState.value = State.Loading(false)
        }
    }
}
