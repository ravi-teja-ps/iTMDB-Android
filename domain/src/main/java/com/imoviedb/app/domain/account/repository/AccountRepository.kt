package com.imoviedb.app.domain.account.repository

import com.imoviedb.app.data.models.account.AccountModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAccountInfo(sessionId : String ) : Flow<AccountModel>

    suspend fun getStoredSessionId() : Flow<String>
}