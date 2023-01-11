package com.imoviedb.app.ui.account.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.account.usecase.GetAccountUseCase
import com.imoviedb.app.domain.authentication.normaluser.usecase.GetUserSessionUseCase
import com.imoviedb.app.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountUseCase: GetAccountUseCase,
                                           private val getUserSessionUseCase: GetUserSessionUseCase
                             ) : BaseViewModel() {

    private val _dataState : MutableStateFlow<State> = MutableStateFlow(State.Loading(true))
    var dataState = _dataState

    fun getAccountData( ){
        viewModelScope.launch {
            getUserSessionUseCase.getUserSession().collect{
                accountUseCase.getAccountInfo(it).catch {
                    //catch for errors
                }.collect{
                    _dataState.value = State.OnComplete(it)
                }
            }
        }
    }
}
