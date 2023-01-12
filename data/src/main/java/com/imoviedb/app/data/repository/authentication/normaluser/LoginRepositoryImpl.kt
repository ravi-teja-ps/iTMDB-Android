package com.imoviedb.app.data.repository.authentication.normaluser

import com.imoviedb.app.data.networking.utils.DispatcherProvider
import com.imoviedb.app.data.dto.authentication.mapper.AccessTokenValidateMapper
import com.imoviedb.app.data.dto.authentication.mapper.NewSessionMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userTokenDAO: UserTokenDAO,
    private val authenticationService: AuthenticationService,
    private val userSessionDAO: UserSessionDAO,
    private val dispatcherProvider: DispatcherProvider,
    private val accessTokenValidateMapper: AccessTokenValidateMapper,
    private val sessionMapper: NewSessionMapper

) : com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository {

    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody) = flow {
        authenticationService.authenticateUserDetails(requestBody = authenticationBody.asMap())
            .body()?.let {
                it.requestToken?.let { _ ->
                    val domainModel = accessTokenValidateMapper.mapDtoToModel(it)
                    userTokenDAO.saveToken(accessTokenValidateMapper.mapModelToEntity(domainModel))
                    emit(domainModel)
                }
            }
    }.flowOn(dispatcherProvider.io)


    override suspend fun createNewSessionIDForUser(requestBody: HashMap<String, String>) =
        flow {
            authenticationService.createSessionID(requestBody = requestBody).body()?.let {
                it.sessionId?.let { _ ->
                    userSessionDAO.removeAllSessions()
                    val domainModel = sessionMapper.dtoToModel(it)
                    userSessionDAO.saveSession(sessionMapper.modelToEntity(domainModel))
                    emit(domainModel)
                }
            }
        }.flowOn(Dispatchers.IO)

}