package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.app.ui.mockedGuestAuthTokenDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAuthenticationUseCase : AuthenticationUseCase {
    override suspend fun createTokenForSession(): Flow<GuestAuthCreateTokenDomainModel> {
        return flow
    }

    override suspend fun deleteGuestToken(): Flow<Unit> {
        return MutableStateFlow(Unit)
    }

    private val flow = MutableStateFlow(mockedGuestAuthTokenDomainModel)
}