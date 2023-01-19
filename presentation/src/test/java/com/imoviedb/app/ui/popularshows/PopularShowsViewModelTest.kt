package com.imoviedb.app.ui.popularshows

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.popularshows.showslist.viewmodel.PopularShowsViewModel
import com.imoviedb.app.ui.BaseTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class PopularShowsViewModelTest : BaseTestClass() {

    lateinit var popularShowsViewModel: PopularShowsViewModel

    @Mock
    private lateinit var popularShowsUseCase: FakePopularShowsUseCase

    override fun postSetup() {
        popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase)

    }

    @Test
    fun getData() {
        assertNotNull(popularShowsViewModel.data)
        assertEquals(popularShowsViewModel.data.value, State.Loading(true))
    }

    @Test
    fun popularShowsViewModel_getPopularShows_invoked() {
        runTest {
            popularShowsUseCase.fetchPopularShows()
            verify(popularShowsUseCase).fetchPopularShows()
        }
    }

    @Test
    fun popularShowsViewModel_getPopularShows_stateflow_result_test() {
        runTest {
            val mockPagingDataFlow = PagingData.from(listOf(ShowDomainModel()))
            doReturn(flowOf(mockPagingDataFlow)).`when`(popularShowsUseCase)
                .fetchPopularShows()
            popularShowsViewModel.getPopularShows()
        }
    }
}