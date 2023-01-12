package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import kotlinx.coroutines.flow.Flow

interface LoginUserUseCase {

     suspend fun validateUserCredential(authenticationBody: AuthenticationBody): Flow<AccessTokenValidateDomainModel>
}


