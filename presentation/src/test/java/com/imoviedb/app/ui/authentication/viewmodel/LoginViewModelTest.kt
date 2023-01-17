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
            fakeLoginUserUseCase, fakeCreateNewSessionUseCase, fakeGuestTokenUseCase,
            dispatcherProvider
        )
    }

    @Test
    fun `test sign in button  happy path`(){
        runTest {
            loginViewModel.setPassword("hello")
            loginViewModel.setUserId("hello")
            loginViewModel.signInButtonStatus.test {
              val result =  awaitItem()
                Assert.assertEquals(result, true)
            }
        }
    }

    @Test
    fun `test sign in button  failure username`(){
        runTest {
            loginViewModel.setPassword("hello")
            loginViewModel.setUserId("")
            loginViewModel.signInButtonStatus.test {
                Assert.assertEquals(awaitItem(), false)
            }
        }
    }

    @Test
    fun `test sign in button failure case`(){
        runTest {
            loginViewModel.setPassword("")
            loginViewModel.setUserId("")
            loginViewModel.signInButtonStatus.test {
                Assert.assertEquals( awaitItem(), false)
            }
        }
    }

    @Test
    fun loginViewModel_flow_data_initial_value() {
        Assert.assertNotNull(loginViewModel.loginScreenUiState)
        Assert.assertEquals(loginViewModel.loginScreenUiState.value, State.Loading(false))
    }

    @Test
    fun loginViewModelLogin_createTokenForSessionInvocation() {
        runTest {
            loginViewModel.login()

            Assert.assertEquals(loginViewModel.loginScreenUiState.value, State.Loading(true))
            verify(fakeGuestTokenUseCase).createTokenForSession(dispatcherProvider.io)
        }
    }

    @Test
    fun loginViewModelLogin_validateCredentials_function() {
        runTest {
            val authenticationBody = AuthenticationBody("", "", "")
            doReturn(flowOf(AccessTokenValidateDomainModel())).`when`(fakeLoginUserUseCase)
                .validateUserCredential(authenticationBody, dispatcherProvider.io)

            fakeLoginUserUseCase.validateUserCredential(authenticationBody, dispatcherProvider.io)

            verify(fakeLoginUserUseCase).validateUserCredential(
                authenticationBody,
                dispatcherProvider.io
            )
        }
    }

    @Test
    fun loginViewModelLogin_createNewSessionPostAuth_function() {
        runTest {

            val mockInput = HashMap<String, String>().apply { put("", "") }
            val mockOutPutModel = NewSessionDomainModel()
            doReturn(flowOf(mockOutPutModel)).`when`(fakeCreateNewSessionUseCase)
                .createNewSession(mockInput, dispatcherProvider.io)

            fakeCreateNewSessionUseCase.createNewSession(mockInput, dispatcherProvider.io)
            verify(fakeCreateNewSessionUseCase).createNewSession(mockInput, dispatcherProvider.io)
            fakeCreateNewSessionUseCase.emit(mockOutPutModel)

            Assert.assertEquals(loginViewModel.loginScreenUiState.value, State.Loading(false))
        }
    }

    @Test
    fun loginViewModelLogin_createTokenForSessionReturnType() {
        runTest {
            val mockedInput = mock(GuestAuthCreateTokenDomainModel::class.java)
            doReturn(flowOf(mockedInput)).`when`(fakeGuestTokenUseCase)
                .createTokenForSession(dispatcherProvider.io)

            fakeGuestTokenUseCase.createTokenForSession(dispatcherProvider.io).collect {
                Assert.assertEquals(mockedInput, it)
            }
        }
    }
}