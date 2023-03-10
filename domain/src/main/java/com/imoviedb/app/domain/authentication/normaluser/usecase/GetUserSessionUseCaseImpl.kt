package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.account.repository.AccountRepository
import javax.inject.Inject

class GetUserSessionUseCaseImpl @Inject constructor(private val accountRepository: AccountRepository) :
    GetUserSessionUseCase {

    override suspend fun getUserSession() = accountRepository.getStoredSessionId()
}