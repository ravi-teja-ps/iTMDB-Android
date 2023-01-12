package com.imoviedb.app.ui.startup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imoviedb.app.data.di.DispatcherProvider
import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import com.imoviedb.app.ui.BaseTestClass
import com.imoviedb.app.ui.core.BaseViewModel
import com.imoviedb.app.ui.startup.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest : BaseTestClass()  {

    @Mock
    private lateinit var fakeAuthenticationUseCase: FakeAuthenticationUseCase

    //class under test
    private lateinit var splashScreenViewModel: SplashScreenViewModel


    override fun setup() {
        initTestWithPrerequisites()
    }


    @Test
    fun splashScreenViewModel_loadAccessTokenWithoutSession_stateFlow_default() {
        runTest {
            splashScreenViewModel = SplashScreenViewModel(fakeAuthenticationUseCase,dispatcherProvider)
            assertEquals(splashScreenViewModel.splashScreenState.value, BaseViewModel.State.Loading(true))
        }
    }

    @Test
    fun splashScreenViewModel_createTokenForSession_invoked_test() {
        runTest {
            splashScreenViewModel = SplashScreenViewModel(fakeAuthenticationUseCase,dispatcherProvider)
            fakeAuthenticationUseCase.createTokenForSession(dispatcherProvider.io)
            verify(fakeAuthenticationUseCase).createTokenForSession(dispatcherProvider.io)
        }
    }

    @Test
    fun splashScreenViewModel_loadAccessTokenWithoutSession_stateFlow_result_test() {
        runTest {
            splashScreenViewModel = SplashScreenViewModel(fakeAuthenticationUseCase,dispatcherProvider)
            val mockEmittingModel = GuestAuthCreateTokenModel()
            doReturn(flowOf(mockEmittingModel)).`when`(fakeAuthenticationUseCase).createTokenForSession(dispatcherProvider.io)

            splashScreenViewModel.loadAccessTokenWithoutSession()

            assertEquals(
                splashScreenViewModel.splashScreenState.value,
                BaseViewModel.State.OnComplete(mockEmittingModel)
            )
        }
    }
}