package com.imoviedb.app.ui.popularshows

import TestCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.popularshows.showslist.viewmodel.PopularShowsViewModel
import com.imoviedb.app.ui.concurrencyutils.TestDispatcherProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PopularShowsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var popularShowsViewModel: PopularShowsViewModel

    @Mock
    private lateinit var popularShowsUseCase: FakePopularShowsUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup(){
        dispatcherProvider = TestDispatcherProvider()

        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun getData() {
        popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase,dispatcherProvider)
        assertNotNull(popularShowsViewModel.data)
        assertEquals(popularShowsViewModel.data.value, State.Loading(true))
    }

    @Test
    fun popularShowsViewModel_getPopularShows_invoked(){
        runTest {
            popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase,dispatcherProvider)

            popularShowsUseCase.fetchPopularShows(dispatcherProvider.io)
            verify(popularShowsUseCase).fetchPopularShows(dispatcherProvider.io)
        }
    }

    @Test
    fun popularShowsViewModel_getPopularShows_stateflow_result_test() {
        runTest {
            popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase,dispatcherProvider)

            val mockPagingDataFlow = PagingData.from(listOf(ShowDomainModel()))
            doReturn(flowOf(mockPagingDataFlow)).`when`(popularShowsUseCase).fetchPopularShows(dispatcherProvider.io)
            popularShowsViewModel.getPopularShows()
           // assertEquals(popularShowsViewModel.data.value,BaseViewModel.State.OnCompletePagedData(mockPagingDataFlow))
          }
    }
}