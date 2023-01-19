package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {

    suspend fun createTokenForSession() : Flow<GuestAuthCreateTokenDomainModel>

    suspend fun deleteGuestToken() : Flow<Unit>

}