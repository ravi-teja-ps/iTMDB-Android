package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor (private val loginRepository: LoginRepository):LoginUserUseCase  {

     override suspend fun validateUserCredential(authenticationBody: AuthenticationBody,coroutineDispatcher: CoroutineDispatcher) =
          loginRepository.validateUserCredential(authenticationBody,coroutineDispatcher)
}


