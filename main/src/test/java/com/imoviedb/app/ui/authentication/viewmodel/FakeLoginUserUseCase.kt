package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.data.models.authentication.AccessTokenValidateModel
import com.imoviedb.app.data.networking.utils.AuthenticationBody
import com.imoviedb.app.domain.authentication.normal_user.usecase.LoginUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLoginUserUseCase : LoginUserUseCase {
    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody): Flow<AccessTokenValidateModel> =
        flow

    private val flow = MutableStateFlow(AccessTokenValidateModel())
    suspend fun emit(value: AccessTokenValidateModel) = flow.emit(value)
}