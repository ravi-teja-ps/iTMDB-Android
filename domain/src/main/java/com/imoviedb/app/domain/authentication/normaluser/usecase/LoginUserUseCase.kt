package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.data.models.authentication.AccessTokenValidateModel
import com.imoviedb.app.data.networking.utils.AuthenticationBody
import kotlinx.coroutines.flow.Flow

interface LoginUserUseCase {

     suspend fun validateUserCredential(authenticationBody: AuthenticationBody): Flow<AccessTokenValidateModel>
}


