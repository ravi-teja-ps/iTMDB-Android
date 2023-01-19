package com.imoviedb.app.ui.authentication.viewmodel

import app.cash.turbine.test
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.app.presentation.ui.authentication.viewmodel.LoginViewModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.ui.BaseTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest : BaseTestClass() {

    //Class to be tested
    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var fakeLoginUserUseCase: FakeLoginUserUseCase

    @Mock
    private lateinit var fakeCreateNewSessionUseCase: FakeCreateNewSessionUsecase

    @Mock
    private lateinit var fakeGuestTokenUseCase: FakeAuthenticationUseCase

    @Before
    override fun postSetup() {
        loginViewModel = LoginViewModel(
            fakeLoginUserUseCase, fakeCreateNewSessionUseCase, fakeGuestTokenUseCase
        )
    }

    @Test
    fun `test sign in button  happy path`() {
        runTest {
            //Arrange
            val mockPassword = "Hello"
            val mockUserId = "hello"

            //Act
            loginViewModel.setPassword(mockPassword)
            loginViewModel.setUserId(mockUserId)

            loginViewModel.signInButtonStatus.test {
                val result = awaitItem()
                //Assertion
                Assert.assertEquals(result, true)
            }
        }
    }

    @Test
    fun `test sign in button  failure username`() {
        runTest {
            //Arrange
            val mockPassword = "Hello"
            val mockUserId = ""

            //Act
            loginViewModel.setPassword(mockPassword)
            loginViewModel.setUserId(mockUserId)

            loginViewModel.signInButtonStatus.test {

                //Assertion
                Assert.assertEquals(awaitItem(), false)
            }
        }
    }

    @Test
    fun `test sign in button failure case`() {
        runTest {
            //Arrange
            val mockPassword = ""
            val mockUserId = ""

            //Act
            loginViewModel.setPassword(mockPassword)
            loginViewModel.setUserId(mockUserId)
            loginViewModel.signInButtonStatus.test {

                //Assertion
                Assert.assertEquals(awaitItem(), false)
            }
        }
    }

    @Test
    fun loginViewModel_flow_data_initial_value() {
        //Arrange
        val initialUiState = loginViewModel.loginScreenUiState

        //Assertions
        Assert.assertNotNull(initialUiState)
        Assert.assertEquals(initialUiState.value, State.Loading(false))
    }

    @Test
    fun loginViewModelLogin_createTokenForSessionInvocation() {
        runTest {
            //Act
            loginViewModel.login()

            //Assertion
            Assert.assertEquals(loginViewModel.loginScreenUiState.value, State.Loading(true))
            verify(fakeGuestTokenUseCase).createTokenForSession()
        }
    }

    @Test
    fun loginViewModelLogin_validateCredentials_function() {
        runTest {
            //Arrange
            val authenticationBody = mock(AuthenticationBody::class.java)
            doReturn(flowOf(AccessTokenValidateDomainModel())).`when`(fakeLoginUserUseCase)
                .validateUserCredential(authenticationBody)

            //Act
            fakeLoginUserUseCase.validateUserCredential(authenticationBody)

            //Assertion or Validation
            verify(fakeLoginUserUseCase).validateUserCredential(authenticationBody)
        }
    }

    @Test
    fun loginViewModelLogin_createNewSessionPostAuth_function() {
        runTest {
            //Arrange
            val mockInput = HashMap<String, String>().apply { put("", "") }
            val mockOutPutModel = NewSessionDomainModel()
            doReturn(flowOf(mockOutPutModel)).`when`(fakeCreateNewSessionUseCase)
                .createNewSession(mockInput)

            //Act
            fakeCreateNewSessionUseCase.createNewSession(mockInput)
            fakeCreateNewSessionUseCase.emit(mockOutPutModel)

            //Assertion
            verify(fakeCreateNewSessionUseCase).createNewSession(mockInput)
            Assert.assertEquals(loginViewModel.loginScreenUiState.value, State.Loading(false))
        }
    }

    @Test
    fun loginViewModelLogin_createTokenForSessionReturnType() {
        runTest {
            //Arrange
            val mockedInput = mock(GuestAuthCreateTokenDomainModel::class.java)
            doReturn(flowOf(mockedInput)).`when`(fakeGuestTokenUseCase).createTokenForSession()

            //Act
            fakeGuestTokenUseCase.createTokenForSession().collect {

                //Assertion
                Assert.assertEquals(mockedInput, it)
            }
        }
    }
}