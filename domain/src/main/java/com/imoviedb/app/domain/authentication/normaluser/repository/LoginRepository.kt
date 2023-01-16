package com.imoviedb.app.domain.authentication.normaluser.repository

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow


interface LoginRepository  {

    suspend fun validateUserCredential(authenticationBody: AuthenticationBody,coroutineDispatcher: CoroutineDispatcher) : Flow<AccessTokenValidateDomainModel>

    suspend fun createNewSessionIDForUser(requestBody: HashMap<String,String>,coroutineDispatcher: CoroutineDispatcher) : Flow<NewSessionDomainModel>

}