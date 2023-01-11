package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {

    suspend fun createTokenForSession(coroutineDispatcher:CoroutineDispatcher) : Flow<GuestAuthCreateTokenModel>

    suspend fun deleteGuestToken(coroutineDispatcher: CoroutineDispatcher) : Flow<Unit>

}