package com.imoviedb.app.ui.popularshows.showlist

import androidx.paging.PagingData
import app.cash.turbine.test
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.ui.base.UiState
import com.imoviedb.app.presentation.ui.popularshows.showslist.viewmodel.PopularShowsViewModel
import com.imoviedb.app.ui.BaseTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class PopularShowsViewModelTest : BaseTestClass() {

    //Class to be tested
    lateinit var popularShowsViewModel: PopularShowsViewModel

    @Mock
    private lateinit var popularShowsUseCase: FakePopularShowsUseCase

    override fun postSetup() {
        popularShowsViewModel = PopularShowsViewModel(popularShowsUseCase)

    }

    @Test
    fun getData() {
        //Arrange
        val initialUiState = UiState.Loading(true)

        //Assertion
        assertNotNull(popularShowsViewModel.data)
        assertEquals(popularShowsViewModel.data.value, initialUiState)
    }

    @Test
    fun popularShowsViewModel_getPopularShows_invoked() {
        runTest {
            //Act
            popularShowsUseCase.fetchPopularShows()

            //Assertion
            verify(popularShowsUseCase).fetchPopularShows()
        }
    }

    @Test
    fun popularShowsViewModel_getPopularShows_stateflow_result_test() {
        runTest {
            //Arrange
            val mockInput = mock(ShowDomainModel::class.java)
            val mockPaging = listOf(mockInput,mockInput)
            val mockPagingDataFlow = PagingData.from(mockPaging)
            doReturn(flowOf(mockPagingDataFlow)).`when`(popularShowsUseCase)
                .fetchPopularShows()

            //Act
            popularShowsViewModel.getPopularShows()
            popularShowsUseCase.fetchPopularShows().test {

                //Assertion
                assertEquals(awaitItem(), mockPagingDataFlow)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}