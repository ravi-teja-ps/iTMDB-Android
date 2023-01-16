package com.imoviedb.app.ui.startup

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAuthenticationUseCase(repository: GuestUserAuthRepository): AuthenticationUseCase {
    private val flow = MutableStateFlow(GuestAuthCreateTokenDomainModel())

    suspend fun emit(value: GuestAuthCreateTokenDomainModel) = flow.emit(value)
    override suspend fun deleteGuestToken(coroutineDispatcher: CoroutineDispatcher): Flow<Unit> {
        return MutableStateFlow(Unit)
     }

    override suspend fun createTokenForSession(coroutineDispatcher: CoroutineDispatcher): Flow<GuestAuthCreateTokenDomainModel> {
        return flow
    }
}