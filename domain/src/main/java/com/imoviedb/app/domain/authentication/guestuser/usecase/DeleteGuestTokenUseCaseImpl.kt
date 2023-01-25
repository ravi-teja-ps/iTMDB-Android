package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import javax.inject.Inject

class DeleteGuestTokenUseCaseImpl @Inject constructor(private val repository: GuestUserAuthRepository)
    : DeleteGuestTokenUseCase {

    override suspend fun deleteGuestToken() = repository.deletePreviousGuestToken()
}