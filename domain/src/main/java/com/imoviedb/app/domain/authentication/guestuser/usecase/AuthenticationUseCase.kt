package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {

    suspend fun createTokenForSession(): Flow<ResponseWrapper<GuestAuthCreateTokenDomainModel>>
}