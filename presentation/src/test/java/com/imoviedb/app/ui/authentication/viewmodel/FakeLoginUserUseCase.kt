package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLoginUserUseCase : LoginUserUseCase {
    override suspend fun validateUserCredential(
        authenticationBody: AuthenticationBody, coroutineDispatcher: CoroutineDispatcher
    ): Flow<AccessTokenValidateDomainModel> = flow

    private val flow = MutableStateFlow(AccessTokenValidateDomainModel())
}