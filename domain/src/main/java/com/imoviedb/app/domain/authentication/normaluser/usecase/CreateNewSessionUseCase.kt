package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import kotlinx.coroutines.flow.Flow

interface CreateNewSessionUseCase {

    suspend fun createNewSession(validUserAccessToken: HashMap<String, String>): Flow<NewSessionDomainModel>
}