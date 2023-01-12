package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {

    suspend fun createTokenForSession(coroutineDispatcher:CoroutineDispatcher) : Flow<GuestAuthCreateTokenDomainModel>

    suspend fun deleteGuestToken(coroutineDispatcher: CoroutineDispatcher) : Flow<Unit>

}