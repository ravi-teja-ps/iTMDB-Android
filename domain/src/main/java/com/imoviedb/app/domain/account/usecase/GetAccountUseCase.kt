package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.data.models.account.AccountModel
import kotlinx.coroutines.flow.Flow

interface GetAccountUseCase{

    suspend fun getAccountInfo(sessionId:String) : Flow<AccountModel>
}