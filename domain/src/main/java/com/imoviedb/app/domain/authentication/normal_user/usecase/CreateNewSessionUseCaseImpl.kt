package com.imoviedb.app.domain.authentication.normal_user.usecase

import com.imoviedb.app.domain.authentication.normal_user.repository.LoginRepository
import javax.inject.Inject

class CreateNewSessionUseCaseImpl  @Inject constructor (private val loginRepository: LoginRepository):CreateNewSessionUseCase  {

    override suspend fun createNewSession(validUserAccessToken: HashMap<String,String>) = loginRepository.createNewSessionIDForUser(validUserAccessToken)
}