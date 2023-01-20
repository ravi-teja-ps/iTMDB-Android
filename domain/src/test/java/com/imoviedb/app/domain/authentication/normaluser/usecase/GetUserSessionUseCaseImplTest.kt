package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.account.repository.AccountRepository
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GetUserSessionUseCaseImplTest : BaseDomainTestClass() {

    private val accountRepository: AccountRepository = mock()

    private lateinit var useCaseGuestSession: GetUserSessionUseCaseImpl

    override fun postSetup() {
        useCaseGuestSession = GetUserSessionUseCaseImpl(accountRepository)
    }

    @Test
    fun getUserSession() {
        runTest {
            //Arrange
            val sessionMock = flowOf(MOCK_SESSION)
            Mockito.doReturn(sessionMock).`when`(accountRepository).getStoredSessionId()

            //Act
            useCaseGuestSession.getUserSession()
            val result = accountRepository.getStoredSessionId()

            //Assertion
            assertEquals(sessionMock,result)
        }
    }

    companion object MockInputs {
        const val MOCK_SESSION = "aZiaunad"
    }
}