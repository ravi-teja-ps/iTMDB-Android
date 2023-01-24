package com.imoviedb.app.domain.account.repository

import com.imoviedb.app.domain.account.model.AccountDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAccountInfo(sessionId: String): Flow<ResponseWrapper<AccountDomainModel>>

    suspend fun getStoredSessionId(): Flow<String>
}