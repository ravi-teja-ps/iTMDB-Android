package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

open class AuthenticationUseCaseImpl @Inject constructor(val repository: GuestUserAuthRepository) :AuthenticationUseCase {

    override suspend fun createTokenForSession(coroutineDispatcher:CoroutineDispatcher)  = repository.createGuestTokenForSession(coroutineDispatcher)
}