package com.imoviedb.app.domain.authentication.normaluser.repository

import com.imoviedb.app.data.models.authentication.AccessTokenValidateModel
import com.imoviedb.app.data.models.authentication.NewSessionModel
import com.imoviedb.app.data.networking.utils.AuthenticationBody
import kotlinx.coroutines.flow.Flow


interface LoginRepository  {

    suspend fun validateUserCredential(authenticationBody: AuthenticationBody) : Flow<AccessTokenValidateModel>

    suspend fun createNewSessionIDForUser(requestBody: HashMap<String,String>) : Flow<NewSessionModel>

}