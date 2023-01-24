package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface CreateNewSessionUseCase {

    suspend fun createNewSession(validUserAccessToken: HashMap<String, String>): Flow<ResponseWrapper<NewSessionDomainModel>>
}