package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteGuestTokenUseCaseImpl @Inject constructor(val repository: GuestUserAuthRepository) :DeleteGuestTokenUseCase {

    override suspend fun deleteGuestToken(coroutineDispatcher: CoroutineDispatcher) = repository.deletePreviousGuestToken(coroutineDispatcher)

}