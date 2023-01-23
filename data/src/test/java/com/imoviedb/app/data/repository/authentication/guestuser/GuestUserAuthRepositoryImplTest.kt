package com.imoviedb.app.data.repository.authentication.guestuser

import com.imoviedb.app.data.base.BaseDataTestClass
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenErrorDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.storage.authentication.GuestUserTokenDAO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class GuestUserAuthRepositoryImplTest : BaseDataTestClass() {

    private val authenticationService: AuthenticationService = mock()
    private val guestUserTokenDAO: GuestUserTokenDAO = mock()
    private val guestAutTokenDtoDomainMapper: GuestAutTokenDtoDomainMapper = mock()
    private val guestAutTokenModelEntityMapper: GuestAutTokenModelEntityMapper = mock()
    private val guestAuthTokenValidateErrorModelMapper: GuestAutTokenErrorDtoDomainMapper = mock()

    private lateinit var guestUserAuthRepo: GuestUserAuthRepositoryImpl

    override fun onPostSetup() {
        guestUserAuthRepo = GuestUserAuthRepositoryImpl(
            authenticationService,
            guestUserTokenDAO,
            guestAutTokenDtoDomainMapper,
            guestAutTokenModelEntityMapper,
            guestAuthTokenValidateErrorModelMapper,
            dispatcherProvider
        )
    }

    @Test
    fun createGuestTokenForSession() {
        runTest {
            //arrange
            val response = mock(Response::class.java)
            doReturn(response).`when`(authenticationService).createApiToken()

            //Act
            val result = authenticationService.createApiToken()

            //Assertion
            assertEquals(result, response)
        }
    }
}