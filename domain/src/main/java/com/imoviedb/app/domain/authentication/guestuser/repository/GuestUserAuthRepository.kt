package com.imoviedb.app.domain.authentication.guestuser.repository

import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface  GuestUserAuthRepository {


    suspend fun createGuestTokenForSession(coroutineDispatcher: CoroutineDispatcher) : Flow<GuestAuthCreateTokenModel>


    suspend fun deletePreviousGuestToken(coroutineDispatcher: CoroutineDispatcher): Flow<Unit>
}