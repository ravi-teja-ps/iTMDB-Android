package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.domain.account.model.AuthenticationBody
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.app.ui.BaseTestClass
import com.imoviedb.app.ui.core.BaseViewModel
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
    override fun setup() {
        initTestWithPrerequisites()
        loginViewModel = LoginViewModel(
            fakeLoginUserUseCase, fakeCreateNewSessionUseCase, fakeGuestTokenUseCase,
            dispatcherProvider
        )
    }

    @Test
    fun loginViewModel_flow_data_initial_value() {
        Assert.assertNotNull(loginViewModel.loginStatus)
        Assert.assertEquals(loginViewModel.loginStatus.value, BaseViewModel.State.Loading(false))
    }

    @Test
    fun loginViewModelLogin_createTokenForSessionInvocation(){
        runTest {
            val mockUserName = "test"
            val mockPassword = "test"

            loginViewModel.login(mockUserName,mockPassword)
            Assert.assertEquals(loginViewModel.loginStatus.value, BaseViewModel.State.Loading(true))
            verify(fakeGuestTokenUseCase).createTokenForSession(dispatcherProvider.io)

        }
    }

    @Test
    fun loginViewModelLogin_validateCredentials_function(){
        runTest {

            val authenticationBody = AuthenticationBody("", "", "")
            doReturn(flowOf(AccessTokenValidateDto())).`when`(fakeLoginUserUseCase)
                .validateUserCredential(authenticationBody)

            fakeLoginUserUseCase.validateUserCredential(authenticationBody)
            verify(fakeLoginUserUseCase).validateUserCredential(authenticationBody)
        }
    }

    @Test
    fun loginViewModelLogin_createNewSessionPostAuth_function(){
        runTest {

            val mockInput = HashMap<String,String>().apply { put("","") }
            val mockOutPutModel = NewSessionDomainModel()
            doReturn(flowOf(mockOutPutModel)).`when`(fakeCreateNewSessionUseCase)
                .createNewSession(mockInput)

            fakeCreateNewSessionUseCase.createNewSession(mockInput)
            verify(fakeCreateNewSessionUseCase).createNewSession(mockInput)
            fakeCreateNewSessionUseCase.emit(mockOutPutModel)

            Assert.assertEquals(loginViewModel.loginStatus.value,BaseViewModel.State.Loading(false))
        }
    }

    @Test
    fun loginViewModelLogin_createTokenForSessionReturnType(){
        runTest {

            val mockedInput = mock(GuestAuthCreateTokenDomainModel::class.java)
            doReturn(flowOf(mockedInput)).`when`(fakeGuestTokenUseCase)
                .createTokenForSession(dispatcherProvider.io)

            fakeGuestTokenUseCase.createTokenForSession(dispatcherProvider.io).collect{

                Assert.assertEquals(mockedInput,it)
            }
        }
    }
}