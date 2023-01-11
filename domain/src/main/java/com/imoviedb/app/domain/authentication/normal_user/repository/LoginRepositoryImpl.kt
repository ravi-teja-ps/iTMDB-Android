package com.imoviedb.app.domain.authentication.normal_user.repository

import com.imoviedb.app.data.di.DispatcherProvider
import com.imoviedb.app.data.models.authentication.NewSessionModel
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.AuthenticationBody
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import com.imoviedb.app.data.storage.authentication.UserTokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val userTokenDAO: UserTokenDAO,
                                              private val authenticationService: AuthenticationService,
                                              private val userSessionDAO: UserSessionDAO,
                                              private val dispatcherProvider: DispatcherProvider
) : LoginRepository {

    override suspend fun validateUserCredential(authenticationBody: AuthenticationBody) = flow {
       authenticationService.authenticateUserDetails(requestBody = authenticationBody.asMap()).body()?.let {

           userTokenDAO.saveToken(UserTokenEntity().apply {
               request_token = it.request_token!!
               expiresAt = it.expiresAt
               status_message = it.status_message
           })
           emit(it)
       }
    }.flowOn(dispatcherProvider.io)


    override suspend fun createNewSessionIDForUser(requestBody: HashMap<String,String>) = flow<NewSessionModel> {
        authenticationService.createSessionID(requestBody = requestBody).body()?.let {
            it.session_id?.let { _Session ->
                userSessionDAO.removeAllSessions()
                userSessionDAO.saveSession(UserSessionEntity().apply {
                    session_id = _Session
                })
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

}