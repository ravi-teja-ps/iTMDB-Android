package com.imoviedb.app.domain.authentication.guestuser.usecase

import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class AuthenticationUseCaseImplTest : BaseDomainTestClass() {

    private lateinit var authenticationUseCaseImpl: AuthenticationUseCaseImpl
    private val repository: GuestUserAuthRepository = mock()

    @Test
    fun createTokenForSession() {
        runTest {
            //Arrange
            val mockedDomainModel = mock(GuestAuthCreateTokenDomainModel::class.java)
            doReturn(flowOf(mockedDomainModel)).`when`(repository).createGuestTokenForSession()

            //Act
            authenticationUseCaseImpl.createTokenForSession()

            //assert or validate
            verify(repository).createGuestTokenForSession()
        }
    }

    override fun postSetup() {
        authenticationUseCaseImpl = AuthenticationUseCaseImpl(repository)
    }
}