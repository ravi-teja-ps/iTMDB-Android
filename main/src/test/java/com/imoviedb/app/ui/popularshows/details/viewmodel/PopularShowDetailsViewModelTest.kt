package com.imoviedb.app.ui.popularshows.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imoviedb.app.data.di.DispatcherProvider
import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.concurrencyutils.TestCoroutineRule
import com.imoviedb.app.data.concurrencyutils.TestDispatcherProvider
import com.imoviedb.app.ui.core.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularShowDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = com.imoviedb.app.data.concurrencyutils.TestCoroutineRule()

    lateinit var popularShowDetailsViewModel: PopularShowDetailsViewModel

    @Mock
    private lateinit var fakePopularShowDetailsUseCase: FakePopularShowDetailsUseCase


    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup(){
        dispatcherProvider = com.imoviedb.app.data.concurrencyutils.TestDispatcherProvider()
        MockitoAnnotations.initMocks(this)
        popularShowDetailsViewModel = PopularShowDetailsViewModel(fakePopularShowDetailsUseCase,dispatcherProvider)

    }

    @Test
    fun popularShowsDetailsViewModel_flow_data_initial_value() {
        assertNotNull(popularShowDetailsViewModel.data)
        Assert.assertEquals(popularShowDetailsViewModel.data.value, BaseViewModel.State.Loading(true))
    }

    @Test
    fun popularShowsDetailsViewModel_testUseCaseInvocation() {
        runTest {
            var mockAccountId = 100
            popularShowDetailsViewModel.getShowDetailsFromDB(mockAccountId)
            verify(fakePopularShowDetailsUseCase).getPopularShowDetails(mockAccountId,dispatcherProvider.default)
        }
    }

    @Test
    fun popularShowsDetailsViewModel_getShowDetailsFromDB() {
        runTest {
            var mockAccountId = 100
            var mockShowObject = mock(Show::class.java)
            Mockito.doReturn(flowOf(mockShowObject)).`when`(fakePopularShowDetailsUseCase)
                .getPopularShowDetails(mockAccountId, dispatcherProvider.io)

            popularShowDetailsViewModel.getShowDetailsFromDB(mockAccountId)

            Assert.assertEquals(
                popularShowDetailsViewModel.data.value,
                BaseViewModel.State.OnComplete(mockShowObject))
        }

    }
}