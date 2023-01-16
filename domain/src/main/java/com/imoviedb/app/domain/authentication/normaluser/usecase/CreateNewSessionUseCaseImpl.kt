package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CreateNewSessionUseCaseImpl  @Inject constructor (private val loginRepository: LoginRepository):CreateNewSessionUseCase  {

    override suspend fun createNewSession(validUserAccessToken: HashMap<String,String>,coroutineDispatcher:CoroutineDispatcher) = loginRepository.createNewSessionIDForUser(validUserAccessToken,coroutineDispatcher)
}