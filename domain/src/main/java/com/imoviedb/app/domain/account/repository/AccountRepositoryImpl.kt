package com.imoviedb.app.domain.account.repository

import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.domain.account.mapper.AccountModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountService: AccountService,
                                                private val accountDAO: AccountDAO,
                                                private val mapper : AccountModelMapper,
                                                private val userSessionDAO: UserSessionDAO) :AccountRepository {

    override suspend fun getAccountInfo(sessionId : String ) =  flow {
        accountService.account(session_id = sessionId).body()?.let {
            //Ensure to delete previous data if any. Can be more ID specific
            //Like usage of Primary key. See update works instead of delete
            accountDAO.deleteAccount()
            accountDAO.insertAccountInfo(mapper.mapModelToEntity(it))
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getStoredSessionId() =  flow<String>{
        emit(userSessionDAO.getSession().session_id)
    }.flowOn(Dispatchers.Default)
}