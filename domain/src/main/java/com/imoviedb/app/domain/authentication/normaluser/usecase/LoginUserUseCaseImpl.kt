package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) :
    LoginUserUseCase {

    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody) =
        loginRepository.validateUserCredential(authenticationBody)
}


