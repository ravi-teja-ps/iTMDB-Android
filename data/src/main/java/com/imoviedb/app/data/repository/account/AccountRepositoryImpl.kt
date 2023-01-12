package com.imoviedb.app.data.repository.account

import com.imoviedb.app.data.dto.account.mapper.AccountModelMapper
import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountService: AccountService,
                                                private val accountDAO: AccountDAO,
                                                private val mapper : AccountModelMapper,
                                                private val userSessionDAO: UserSessionDAO
) : com.imoviedb.app.domain.account.repository.AccountRepository {

    override suspend fun getAccountInfo(sessionId : String ) =  flow {
        accountService.account(session_id = sessionId).body()?.let {
            //Ensure to delete previous data if any. Can be more ID specific
            //Like usage of Primary key. See update works instead of delete
            accountDAO.deleteAccount()
            val domainModel = mapper.mapDtoToDomainModel(it)
            accountDAO.insertAccountInfo(mapper.mapModelToEntity(domainModel))
            emit(domainModel)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getStoredSessionId() =  flow {
        emit(userSessionDAO.getSession().session_id)
    }.flowOn(Dispatchers.Default)
}