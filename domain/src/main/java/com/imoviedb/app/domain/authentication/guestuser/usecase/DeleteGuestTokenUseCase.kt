package com.imoviedb.app.domain.authentication.guestuser.usecase

import kotlinx.coroutines.flow.Flow

interface DeleteGuestTokenUseCase {

    suspend fun deleteGuestToken(): Flow<Unit>
}