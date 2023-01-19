package com.imoviedb.app.domain.account.repository

import com.imoviedb.app.domain.account.model.AccountDomainModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAccountInfo(sessionId: String): Flow<AccountDomainModel>

    suspend fun getStoredSessionId(): Flow<String>
}