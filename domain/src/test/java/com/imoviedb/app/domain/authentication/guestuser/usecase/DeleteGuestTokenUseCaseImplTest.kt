package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteGuestTokenUseCaseImplTest : BaseDomainTestClass() {

    private val repository: GuestUserAuthRepository = Mockito.mock()

    private lateinit var deleteGuestTokenUseCase: DeleteGuestTokenUseCaseImpl

    override fun postSetup() {
        deleteGuestTokenUseCase = DeleteGuestTokenUseCaseImpl(repository)
    }

    @Test
    fun deleteGuestToken() {
        runTest {
            //Act
            deleteGuestTokenUseCase.deleteGuestToken()

            //assert
            verify(repository).deletePreviousGuestToken()
        }
    }
}