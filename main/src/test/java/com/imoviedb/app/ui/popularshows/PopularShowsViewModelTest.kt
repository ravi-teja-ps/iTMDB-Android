package com.imoviedb.app.ui.popularshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.imoviedb.app.data.di.DispatcherProvider
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.ui.core.BaseViewModel
import com.imoviedb.app.ui.popularshows.showslist.viewmodel.PopularShowsViewModel
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
    val testCoroutineRule = com.imoviedb.app.data.concurrencyutils.TestCoroutineRule()

    lateinit var popularShowsViewModel: PopularShowsViewModel

    @Mock
    private lateinit var popularShowsUseCase: FakePopularShowsUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup(){
        dispatcherProvider = com.imoviedb.app.data.concurrencyutils.TestDispatcherProvider()

        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun getData() {
        popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase)
        assertNotNull(popularShowsViewModel.data)
        assertEquals(popularShowsViewModel.data.value,BaseViewModel.State.Loading(true))
    }

    @Test
    fun popularShowsViewModel_getPopularShows_invoked(){
        runTest {
            popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase)

            popularShowsUseCase.fetchPopularShows()
            verify(popularShowsUseCase).fetchPopularShows()
        }
    }

    @Test
    fun popularShowsViewModel_getPopularShows_stateflow_result_test() {
        runTest {
            popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase)

            val mockPagingDataFlow = PagingData.from(listOf(ShowEntityModel()))
            doReturn(flowOf(mockPagingDataFlow)).`when`(popularShowsUseCase).fetchPopularShows()
            popularShowsViewModel.getPopularShows()
            assertEquals(popularShowsViewModel.data.value,BaseViewModel.State.OnCompletePagedData(mockPagingDataFlow))
          }
    }
}