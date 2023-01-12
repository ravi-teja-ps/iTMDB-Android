package com.imoviedb.app.domain.authentication.guestuser.repository

import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface  GuestUserAuthRepository {


    suspend fun createGuestTokenForSession(coroutineDispatcher: CoroutineDispatcher) : Flow<GuestAuthCreateTokenDomainModel>


    suspend fun deletePreviousGuestToken(coroutineDispatcher: CoroutineDispatcher): Flow<Unit>
}