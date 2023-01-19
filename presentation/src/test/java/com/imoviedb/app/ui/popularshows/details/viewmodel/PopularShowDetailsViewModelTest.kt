package com.imoviedb.app.ui.popularshows.details.viewmodel

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.popularshows.details.viewmodel.PopularShowDetailsViewModel
import com.imoviedb.app.ui.BaseTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularShowDetailsViewModelTest : BaseTestClass() {
    //Class to be tested
    private lateinit var popularShowDetailsViewModel: PopularShowDetailsViewModel

    @Mock
    private lateinit var fakePopularShowDetailsUseCase: FakePopularShowDetailsUseCase

    @Before
    override fun postSetup() {
        popularShowDetailsViewModel =
            PopularShowDetailsViewModel(fakePopularShowDetailsUseCase)
    }

    @Test
    fun popularShowsDetailsViewModel_flow_data_initial_value() {
        //Arrange
        val expectedState = State.Loading(true)

        //Assertion
        assertNotNull(popularShowDetailsViewModel.data)
        assertEquals(popularShowDetailsViewModel.data.value, expectedState)
    }

    @Test
    fun popularShowsDetailsViewModel_testUseCaseInvocation() {
        runTest {
            //Arrange
            val mockAccountId = 100

            //Act
            popularShowDetailsViewModel.getShowDetailsFromDB(mockAccountId)

            //Assert or Verify
            verify(fakePopularShowDetailsUseCase).getPopularShowDetails(mockAccountId)
        }
    }

    @Test
    fun popularShowsDetailsViewModel_getShowDetailsFromDB() {
        runTest {
            //Arrange
            val mockAccountId = 100
            val mockShowObjectDto = mock(ShowDomainModel::class.java)
            doReturn(flowOf(mockShowObjectDto)).`when`(fakePopularShowDetailsUseCase)
                .getPopularShowDetails(mockAccountId)

            //Act
            popularShowDetailsViewModel.getShowDetailsFromDB(mockAccountId)

            //Assertion
            assertEquals(popularShowDetailsViewModel.data.value, State.OnComplete(mockShowObjectDto))
        }
    }
}