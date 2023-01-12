package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import com.imoviedb.app.domain.authentication.guestuser.usecase.AuthenticationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeAuthenticationUseCase : AuthenticationUseCase {
    override suspend fun createTokenForSession(coroutineDispatcher: CoroutineDispatcher): Flow<GuestAuthCreateTokenModel> {
       return flow
    }

    override suspend fun deleteGuestToken(coroutineDispatcher: CoroutineDispatcher): Flow<Unit> {
         return MutableStateFlow(Unit)
    }


    private val flow = MutableStateFlow(GuestAuthCreateTokenModel())
}