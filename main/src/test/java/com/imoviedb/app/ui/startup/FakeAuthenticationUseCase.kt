package com.imoviedb.app.ui.startup

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAuthenticationUseCase(repository: GuestUserAuthRepository): AuthenticationUseCase {
    private val flow = MutableStateFlow(GuestAuthCreateTokenDto())

    suspend fun emit(value: GuestAuthCreateTokenDto) = flow.emit(value)
    override suspend fun deleteGuestToken(coroutineDispatcher: CoroutineDispatcher): Flow<Unit> {
        return MutableStateFlow(Unit)
     }

    override suspend fun createTokenForSession(coroutineDispatcher: CoroutineDispatcher): Flow<GuestAuthCreateTokenDto> {
        return flow
    }
}