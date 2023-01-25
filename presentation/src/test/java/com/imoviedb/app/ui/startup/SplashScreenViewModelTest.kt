package com.imoviedb.app.ui.startup

import com.imoviedb.app.domain.authentication.guestuser.usecase.DeleteGuestTokenUseCaseImpl
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.app.presentation.ui.startup.viewmodel.SplashScreenViewModel
import com.imoviedb.app.ui.BaseTestClass
import com.imoviedb.app.ui.authentication.viewmodel.FakeAuthenticationUseCase
import com.imoviedb.app.ui.mockedGuestAuthTokenDomainModel
import com.imoviedb.common.state.ResponseWrapper
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

    private val deleteGuestTokenUseCaseImpl: DeleteGuestTokenUseCaseImpl = mock()

    private lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun postSetup() {
        splashScreenViewModel =
            SplashScreenViewModel(authenticationUseCase, deleteGuestTokenUseCaseImpl)
    }

    @Test
    fun getSplashScreenState() {
        //Arrange
        val expectedUiState = UiState.Loading(true)

        //Assertion
        Assert.assertNotNull(splashScreenViewModel.splashScreenState)
        Assert.assertEquals(splashScreenViewModel.splashScreenState.value, expectedUiState)
    }

    @Test
    fun loadAccessTokenWithoutSession() {
        runTest {
            //arrange
            Mockito.doReturn(flowOf(ResponseWrapper.Success(mockedGuestAuthTokenDomainModel)))
                .`when`(authenticationUseCase).createTokenForSession()

            //Act
            deleteGuestTokenUseCaseImpl.deleteGuestToken()

            authenticationUseCase.createTokenForSession().collect {

                //Assert
                assertEquals(it.data, mockedGuestAuthTokenDomainModel)
            }
        }
    }
}