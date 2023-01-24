package com.imoviedb.app.data.repository.account

import com.imoviedb.app.data.dto.account.mapper.AccountDtoModelMapper
import com.imoviedb.app.data.dto.account.mapper.AccountModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.networking.utils.asErrorModel
import com.imoviedb.app.data.networking.utils.isSuccess
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.domain.account.repository.AccountRepository
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
    private val accountDAO: AccountDAO,
    private val dtoModelMapper: AccountDtoModelMapper,
    private val modelEntityMapper: AccountModelEntityMapper,
    private val userSessionDAO: UserSessionDAO,
    private val dispatcherProvider: DispatcherProvider
) : AccountRepository {

    override suspend fun getAccountInfo(sessionId: String) = flow {
        val response = accountService.account(session_id = sessionId)
        if (response.isSuccess()) {
            response.body()?.let {
                //Ensure to delete previous data if any.
                accountDAO.deleteAccount()
                with(dtoModelMapper.map(it)){
                    accountDAO.insertAccountInfo(modelEntityMapper.map(this))
                    emit(ResponseWrapper.Success(this))
                }
            }
        } else {
            with(response.asErrorModel()){
                emit(ResponseWrapper.Error(statusCode,statusMessage))
            }
        }
    }.flowOn(dispatcherProvider.io)

    override suspend fun getStoredSessionId() = flow {
        emit(userSessionDAO.getSession().sessionId)
    }.flowOn(dispatcherProvider.default)
}