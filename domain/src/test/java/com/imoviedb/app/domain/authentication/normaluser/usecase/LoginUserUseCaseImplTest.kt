package com.imoviedb.app.domain.authentication.normaluser.usecase

import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUserUseCaseImplTest : BaseDomainTestClass() {

    private val loginRepository: LoginRepository = mock()

    //Class under test
    private lateinit var loginUseCase: LoginUserUseCaseImpl

    override fun postSetup() {
        loginUseCase = LoginUserUseCaseImpl(loginRepository)
    }

    @Test
    fun `validate UserCredential function`() {
        runTest {
            //Arrange
            val authBody = mock(AuthenticationBody::class.java)
            val domainModel = mock(AccessTokenValidateDomainModel::class.java)
            val stubObject = flowOf(domainModel)
            doReturn(stubObject).`when`(loginRepository).validateUserCredential(authBody)

            //Act
            loginUseCase.validateUserCredential(authBody)
            val result = loginRepository.validateUserCredential(authBody)

            //Assertion
            assertEquals(stubObject, result)
        }
    }


}