package com.imoviedb.app.domain.authentication.normal_user.usecase

import kotlinx.coroutines.flow.Flow

interface GetUserSessionUseCase {

    suspend fun getUserSession() : Flow<String>
}