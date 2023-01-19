package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.domain.account.model.AccountDomainModel
import kotlinx.coroutines.flow.Flow

interface GetAccountUseCase {

    suspend fun getAccountInfo(sessionId: String): Flow<AccountDomainModel>
}