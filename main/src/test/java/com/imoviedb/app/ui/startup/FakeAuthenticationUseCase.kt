package com.imoviedb.app.ui.startup

import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAuthenticationUseCase(repository: GuestUserAuthRepository) : AuthenticationUseCaseImpl( repository) {
    private val flow = MutableStateFlow(GuestAuthCreateTokenModel())
    suspend fun emit(value: GuestAuthCreateTokenModel) = flow.emit(value)
    override suspend fun createTokenForSession(coroutineDispatcher: CoroutineDispatcher): Flow<GuestAuthCreateTokenModel> {
        return flow
    }
}