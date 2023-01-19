package com.imoviedb.app.data.repository.login

import com.imoviedb.app.data.base.BaseDomainTestClass
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateDtoModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateErrorModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateModelEntityMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionErrorDtoModelMapper
import com.imoviedb.app.data.dto.authentication.mapper.newsession.NewSessionModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.repository.authentication.normaluser.LoginRepositoryImpl
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import com.imoviedb.app.data.storage.authentication.UserTokenEntity
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
class LoginRepositoryTest : BaseDomainTestClass() {

    @Mock
    private lateinit var userTokenDAO: UserTokenDAO

    @Mock
    private lateinit var authenticationService: AuthenticationService

    @Mock
    private lateinit var userSessionDAO: UserSessionDAO

    @Mock
    private lateinit var accessTokenDtoModelMapper: AccessTokenValidateDtoModelMapper

    @Mock
    private lateinit var accessTokenModelEntityMapper: AccessTokenValidateModelEntityMapper

    @Mock
    private lateinit var accessTokenErrorModelMapper: AccessTokenValidateErrorModelMapper

    @Mock
    private lateinit var newSessionDtoDomainMapper: NewSessionDtoDomainMapper

    @Mock
    private lateinit var newSessionErrorDtoModelMapper: NewSessionErrorDtoModelMapper

    @Mock
    private lateinit var newSessionModelEntityMapper: NewSessionModelEntityMapper

    private lateinit var loginRepository: LoginRepository

    override fun onPostSetup() {
        loginRepository = LoginRepositoryImpl(
            userTokenDAO,
            authenticationService,
            userSessionDAO,
            accessTokenDtoModelMapper,
            accessTokenModelEntityMapper,
            accessTokenErrorModelMapper,
            newSessionDtoDomainMapper,
            newSessionErrorDtoModelMapper,
            newSessionModelEntityMapper,
            dispatcherProvider
        )
    }


    @Test
    fun validateUserCredential() {
        runTest {

            val mockInputData = AuthenticationBody("a", "b", "iAz123kaa")
            val userTokenEntityMock = mock(Response::class.java)
            loginRepository.validateUserCredential(mockInputData)
            Mockito.doReturn(userTokenEntityMock).`when`(authenticationService)
                .authenticateUserDetails(requestBody = mockInputData.asMap())


            val result =
                (authenticationService.authenticateUserDetails(requestBody = mockInputData.asMap()))
            assertEquals(result, userTokenEntityMock)
        }
    }

    @Test
    fun validateUserTokenSavedToDB_func_invoked() {
        runTest {

            val mockInputData = AuthenticationBody("a", "b", "iAz123kaa")
            val mockedUserTokenEntity = mock(UserTokenEntity::class.java)
            loginRepository.validateUserCredential(mockInputData)
            userTokenDAO.saveToken(mockedUserTokenEntity)
            verify(userTokenDAO).saveToken(mockedUserTokenEntity)
        }
    }


    @Test
    fun loginRespo_test_createNewSessionIDForUser() {
        runTest {
            val mockInputData = HashMap<String, String>().apply { put("a", "b") }
            val userTokenEntityMock = mock(Response::class.java)
            val userSessionEntity = mock(UserSessionEntity::class.java)
            loginRepository.createNewSessionIDForUser(mockInputData)
            Mockito.doReturn(userTokenEntityMock).`when`(authenticationService)
                .createSessionID(requestBody = mockInputData)

            val result = authenticationService.createSessionID(requestBody = mockInputData)
            assertEquals(result, userTokenEntityMock)
            userSessionDAO.removeAllSessions()
            userSessionDAO.saveSession(userSessionEntity)
            verify(userSessionDAO).removeAllSessions()
            verify(userSessionDAO).saveSession(userSessionEntity)

        }
    }
}