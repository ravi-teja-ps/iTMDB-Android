package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.data.models.account.AccountModel
import com.imoviedb.app.domain.account.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGetAccountUseCaseRepository : AccountRepository {

    private val flowAccessTokenModel = MutableStateFlow(AccountModel())
    suspend fun emit(value: AccountModel) = flowAccessTokenModel.emit(value)

    private val flowSessionId = MutableStateFlow("")
    suspend fun emit(value: String) = flowSessionId.emit(value)

    override suspend fun getAccountInfo(sessionId: String): Flow<AccountModel> {
         return flowAccessTokenModel
    }

    override suspend fun getStoredSessionId(): Flow<String> {
        return flowSessionId
    }
}