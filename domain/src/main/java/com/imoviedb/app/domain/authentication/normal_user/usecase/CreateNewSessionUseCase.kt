package com.imoviedb.app.domain.authentication.normal_user.usecase

import com.imoviedb.app.data.models.authentication.NewSessionModel
import kotlinx.coroutines.flow.Flow

interface CreateNewSessionUseCase {

    suspend fun createNewSession(validUserAccessToken: HashMap<String,String>) : Flow<NewSessionModel>
}