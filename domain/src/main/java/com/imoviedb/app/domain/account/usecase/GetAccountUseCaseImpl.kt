package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.domain.account.repository.AccountRepository
import javax.inject.Inject

class GetAccountUseCaseImpl @Inject constructor(private val accountRepository: AccountRepository) :
    GetAccountUseCase {

    override suspend fun getAccountInfo(sessionId: String) =
        accountRepository.getAccountInfo(sessionId)
}