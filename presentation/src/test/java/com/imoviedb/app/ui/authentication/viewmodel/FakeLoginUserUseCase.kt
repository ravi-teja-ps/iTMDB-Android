package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.normaluser.usecase.LoginUserUseCase
import com.imoviedb.app.ui.mockedAccessTokenDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLoginUserUseCase : LoginUserUseCase {
    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody): Flow<ResponseWrapper<AccessTokenValidateDomainModel>> {
        return flow
    }

    private val flow = MutableStateFlow(ResponseWrapper.Success(mockedAccessTokenDomainModel))
}