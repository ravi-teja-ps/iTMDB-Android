package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.domain.account.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGetAccountUseCaseRepository : AccountRepository {

    private val flowAccessTokenModel = MutableStateFlow(AccountDto())
    suspend fun emit(value: AccountDto) = flowAccessTokenModel.emit(value)

    private val flowSessionId = MutableStateFlow("")
    suspend fun emit(value: String) = flowSessionId.emit(value)

    override suspend fun getAccountInfo(sessionId: String): Flow<AccountDto> {
         return flowAccessTokenModel
    }

    override suspend fun getStoredSessionId(): Flow<String> {
        return flowSessionId
    }
}