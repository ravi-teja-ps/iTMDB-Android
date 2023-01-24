package com.imoviedb.app.domain.authentication.normaluser.repository

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    suspend fun validateUserCredential(authenticationBody: AuthenticationBody): Flow<ResponseWrapper<AccessTokenValidateDomainModel>>

    suspend fun createNewSessionIDForUser(requestBody: HashMap<String, String>): Flow<ResponseWrapper<NewSessionDomainModel>>

}