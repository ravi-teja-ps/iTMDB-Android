package com.imoviedb.app.domain.authentication.guestuser.repository

import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface GuestUserAuthRepository {
    suspend fun createGuestTokenForSession(): Flow<ResponseWrapper<GuestAuthCreateTokenDomainModel>>

    suspend fun deletePreviousGuestToken(): Flow<Unit>
}