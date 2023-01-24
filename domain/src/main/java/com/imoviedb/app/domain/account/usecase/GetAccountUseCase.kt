package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.domain.account.model.AccountDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface GetAccountUseCase {

    suspend fun getAccountInfo(sessionId: String): Flow<ResponseWrapper<AccountDomainModel>>
}