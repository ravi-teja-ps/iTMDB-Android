package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import javax.inject.Inject

class AuthenticationUseCaseImpl @Inject constructor(private val repository: GuestUserAuthRepository) :AuthenticationUseCase {

    override suspend fun createTokenForSession()  = repository.createGuestTokenForSession()

    override suspend fun deleteGuestToken() = repository.deletePreviousGuestToken()

}