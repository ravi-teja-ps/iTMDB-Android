package com.imoviedb.app.domain.authentication.normaluser.usecase

import kotlinx.coroutines.flow.Flow

interface GetUserSessionUseCase {

    suspend fun getUserSession() : Flow<String>
}