package com.imoviedb.app.domain.authentication.normal_user.usecase

import com.imoviedb.app.data.networking.utils.AuthenticationBody
import com.imoviedb.app.domain.authentication.normal_user.repository.LoginRepository
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor (private val loginRepository: LoginRepository):LoginUserUseCase  {

     override suspend fun validateUserCredential(authenticationBody: AuthenticationBody) =
          loginRepository.validateUserCredential(authenticationBody)
}


