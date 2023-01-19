package com.imoviedb.app.data.repository.authentication.normaluser

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateDtoModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateErrorModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateModelEntityMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AuthenticationBodyMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionErrorDtoModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.toErrorModel
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userTokenDAO: UserTokenDAO,
    private val authenticationService: AuthenticationService,
    private val userSessionDAO: UserSessionDAO,
    private val accessTokenDtoModelMapper: AccessTokenValidateDtoModelMapper,
    private val accessTokenModelEntityMapper: AccessTokenValidateModelEntityMapper,
    private val accessTokenErrorModelMapper: AccessTokenValidateErrorModelMapper,

    private val newSessionDtoDomainMapper: NewSessionDtoDomainMapper,
    private val newSessionErrorDtoModelMapper: NewSessionErrorDtoModelMapper,
    private val newSessionModelEntityMapper: NewSessionModelEntityMapper,
    private val authenticationBodyMapper: AuthenticationBodyMapper,
    private val dispatcherProvider: DispatcherProvider
) : com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository {

    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody) = flow {
        val response = authenticationService.authenticateUserDetails(requestBody = authenticationBodyMapper.map(authenticationBody))
        if(response.isSuccessful && response.body() != null){
            response.body()?.let {
                it.requestToken?.let { _ ->
                    val domainModel = accessTokenDtoModelMapper.map(it)
                    userTokenDAO.saveToken(accessTokenModelEntityMapper.map(domainModel))
                    emit(domainModel)
                }
            }
        }else{
            val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
            emit(accessTokenErrorModelMapper.map(errorModel))
        }
    }.flowOn(dispatcherProvider.io)


    override suspend fun createNewSessionIDForUser(requestBody: HashMap<String, String>) =
        flow {
            val response = authenticationService.createSessionID(requestBody = requestBody)
            if(response.isSuccessful && response.body() != null){
                response.body()?.let {
                    it.sessionId?.let { _ ->
                        userSessionDAO.removeAllSessions()
                        val domainModel = newSessionDtoDomainMapper.map(it)
                        userSessionDAO.saveSession(newSessionModelEntityMapper.map(domainModel))
                        emit(domainModel)
                    }
                }
            }else{
                val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
                emit(newSessionErrorDtoModelMapper.map(errorModel))
            }


        }.flowOn(dispatcherProvider.io)

}