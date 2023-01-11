package com.imoviedb.app.domain.authentication.guestuser.repository

import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.storage.authentication.GuestUserTokenDAO
import convertModelToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GuestUserAuthRepositoryImpl @Inject constructor(private val authenticationService: AuthenticationService,
                                                      private val guestUserTokenDAO: GuestUserTokenDAO
) :GuestUserAuthRepository {


    override suspend fun createGuestTokenForSession(coroutineDispatcher: CoroutineDispatcher) = flow {

        val authenticationCreateTokenModel = authenticationService.createApiToken().body()!!
        authenticationCreateTokenModel?.let {
            //Store data token in local storage
            guestUserTokenDAO.saveToken(it.convertModelToEntity())
            //send data to UI and view model
            emit (authenticationCreateTokenModel)
        }
    }.flowOn(coroutineDispatcher)


    override suspend fun deletePreviousGuestToken(coroutineDispatcher: CoroutineDispatcher) = flow<Unit> {
        guestUserTokenDAO.removeToken()
    }.flowOn(coroutineDispatcher)
}