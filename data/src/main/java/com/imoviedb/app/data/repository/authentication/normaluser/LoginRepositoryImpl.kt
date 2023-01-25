package com.imoviedb.app.data.repository.authentication.normaluser

import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateDtoModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateModelEntityMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AuthenticationBodyMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.asErrorModel
import com.imoviedb.app.data.networking.utils.isSuccess
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import com.imoviedb.common.state.ResponseWrapper
import com.imoviedb.app.data.networking.utils.toResponseWrapper
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userTokenDAO: UserTokenDAO,
    private val authenticationService: AuthenticationService,
    private val userSessionDAO: UserSessionDAO,
    private val accessTokenDtoModelMapper: AccessTokenValidateDtoModelMapper,
    private val accessTokenModelEntityMapper: AccessTokenValidateModelEntityMapper,

    private val newSessionDtoDomainMapper: NewSessionDtoDomainMapper,
    private val newSessionModelEntityMapper: NewSessionModelEntityMapper,
    private val authenticationBodyMapper: AuthenticationBodyMapper,
    private val dispatcherProvider: DispatcherProvider
) : com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository {

    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody) = flow {
        try {
            val response = authenticationService.authenticateUserDetails(
                requestBody = authenticationBodyMapper.map(authenticationBody)
            )
            if (response.isSuccess()) {
                response.body()?.let {
                    it.requestToken.let { _ ->
                        with(accessTokenDtoModelMapper.map(it)) {
                            userTokenDAO.saveToken(accessTokenModelEntityMapper.map(this))
                            emit(ResponseWrapper.Success(this))
                        }
                    }
                }
            } else {
                with(response.asErrorModel()) {
                    emit(ResponseWrapper.Error(statusCode, statusMessage))
                }
            }
        } catch (exception : Exception){
            emit(exception.toResponseWrapper())
        }
    }.flowOn(dispatcherProvider.io)


    override suspend fun createNewSessionIDForUser(requestBody: HashMap<String, String>) =
        flow {
            try {
                val response = authenticationService.createSessionID(requestBody = requestBody)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        it.sessionId.let { _ ->
                            userSessionDAO.removeAllSessions()
                            with(newSessionDtoDomainMapper.map(it)) {
                                userSessionDAO.saveSession(newSessionModelEntityMapper.map(this))
                                emit(ResponseWrapper.Success(this))
                            }
                        }
                    }
                } else {
                    with(response.asErrorModel()) {
                        emit(ResponseWrapper.Error(statusCode, statusMessage))
                    }
                }
            }catch (exception : Exception){
                emit(exception.toResponseWrapper())
            }
        }.flowOn(dispatcherProvider.io)

}