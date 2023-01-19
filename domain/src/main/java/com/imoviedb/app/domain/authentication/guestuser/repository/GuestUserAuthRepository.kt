package com.imoviedb.app.domain.authentication.guestuser.repository

import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import kotlinx.coroutines.flow.Flow

interface  GuestUserAuthRepository {


    suspend fun createGuestTokenForSession() : Flow<GuestAuthCreateTokenDomainModel>


    suspend fun deletePreviousGuestToken(): Flow<Unit>
}