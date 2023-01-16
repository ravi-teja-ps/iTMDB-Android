package com.imoviedb.app.data.repository.account

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.account.mapper.AccountDtoModelMapper
import com.imoviedb.app.data.dto.account.mapper.AccountErrorModelMapper
import com.imoviedb.app.data.dto.account.mapper.AccountModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.networking.utils.toErrorModel
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.domain.account.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountService: AccountService,
                                                private val accountDAO: AccountDAO,
                                                private val dtoModelMapper : AccountDtoModelMapper,
                                                private val modelEntityMapper: AccountModelEntityMapper,
                                                private val errorModelMapper: AccountErrorModelMapper,
                                                private val userSessionDAO: UserSessionDAO
) : AccountRepository {

    override suspend fun getAccountInfo(sessionId : String ) =  flow {

        val response = accountService.account(session_id = sessionId)
        if(response.isSuccessful && response.body()!= null){
            response.body()?.let {
                //Ensure to delete previous data if any. Can be more ID specific
                //Like usage of Primary key. See update works instead of delete
                accountDAO.deleteAccount()
                val domainModel = dtoModelMapper.map(it)
                accountDAO.insertAccountInfo(modelEntityMapper.map(domainModel))
                emit(domainModel)
            }
        }
        else {
            val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
            emit(errorModelMapper.map(errorModel))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getStoredSessionId() =  flow {
        emit(userSessionDAO.getSession().session_id)
    }.flowOn(Dispatchers.Default)
}