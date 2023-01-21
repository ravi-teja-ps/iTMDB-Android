package com.imoviedb.app.ui.startup

import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.startup.viewmodel.SplashScreenViewModel
import com.imoviedb.app.ui.BaseTestClass
import com.imoviedb.app.ui.authentication.viewmodel.FakeAuthenticationUseCase
import com.imoviedb.app.ui.mockedGuestAuthTokenDomainModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class SplashScreenViewModelTest : BaseTestClass() {

    private val authenticationUseCase: FakeAuthenticationUseCase = mock()

    private lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun postSetup() {
        splashScreenViewModel = SplashScreenViewModel(authenticationUseCase)
    }

    @Test
    fun getSplashScreenState() {
        //Arrange
        val expectedState = State.Loading(true)

        //Assertion
        Assert.assertNotNull(splashScreenViewModel.splashScreenState)
        Assert.assertEquals(splashScreenViewModel.splashScreenState.value, expectedState)
    }

    @Test
    fun loadAccessTokenWithoutSession() {
        runTest {
            //arrange
            Mockito.doReturn(flowOf(mockedGuestAuthTokenDomainModel)).`when`(authenticationUseCase).createTokenForSession()

            //Act
             authenticationUseCase.createTokenForSession().collect{

                 //Assert
                assertEquals(it, mockedGuestAuthTokenDomainModel)
            }
        }
    }
}