package com.imoviedb.app.data.repository.authentication.normaluser

import com.imoviedb.app.data.dto.ErrorResponseDto
import com.imoviedb.app.data.dto.authentication.mapper.AccessTokenValidateMapper
import com.imoviedb.app.data.dto.authentication.mapper.NewSessionMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.toErrorModel
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import com.imoviedb.app.domain.account.model.AuthenticationBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userTokenDAO: UserTokenDAO,
    private val authenticationService: AuthenticationService,
    private val userSessionDAO: UserSessionDAO,
    private val accessTokenValidateMapper: AccessTokenValidateMapper,
    private val sessionMapper: NewSessionMapper

) : com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository {

    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody,coroutineDispatcher: CoroutineDispatcher) = flow {
        val response = authenticationService.authenticateUserDetails(requestBody = authenticationBody.asMap())
        if(response.isSuccessful && response.body() != null){
            response.body()?.let {
                it.requestToken?.let { _ ->
                    val domainModel = accessTokenValidateMapper.mapDtoToModel(it)
                    userTokenDAO.saveToken(accessTokenValidateMapper.mapModelToEntity(domainModel))
                    emit(domainModel)
                }
            }
        }else{
            val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
            emit(accessTokenValidateMapper.mapErrorDtoToModel(errorModel))
        }
    }.flowOn(coroutineDispatcher)


    override suspend fun createNewSessionIDForUser(requestBody: HashMap<String, String>,coroutineDispatcher: CoroutineDispatcher) =
        flow {
            val response = authenticationService.createSessionID(requestBody = requestBody)
            if(response.isSuccessful && response.body() != null){
                response.body()?.let {
                    it.sessionId?.let { _ ->
                        userSessionDAO.removeAllSessions()
                        val domainModel = sessionMapper.dtoToModel(it)
                        userSessionDAO.saveSession(sessionMapper.modelToEntity(domainModel))
                        emit(domainModel)
                    }
                }
            }else{
                val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
                emit(sessionMapper.errorDtoToModel(errorModel))
            }


        }.flowOn(coroutineDispatcher)

}