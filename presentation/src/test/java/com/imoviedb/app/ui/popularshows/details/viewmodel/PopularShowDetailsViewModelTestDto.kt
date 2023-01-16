package com.imoviedb.app.ui.popularshows.details.viewmodel

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.popularshows.details.viewmodel.PopularShowDetailsViewModel
import com.imoviedb.app.ui.BaseTestClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularShowDetailsViewModelTestDto : BaseTestClass() {

    private lateinit var popularShowDetailsViewModel: PopularShowDetailsViewModel

    @Mock
    private lateinit var fakePopularShowDetailsUseCase: FakePopularShowDetailsUseCase

    @Before
    override fun setup(){
        initTestWithPrerequisites()
        popularShowDetailsViewModel = PopularShowDetailsViewModel(fakePopularShowDetailsUseCase,dispatcherProvider)
    }


    @Test
    fun popularShowsDetailsViewModel_flow_data_initial_value() {
        assertNotNull(popularShowDetailsViewModel.data)
        Assert.assertEquals(popularShowDetailsViewModel.data.value, State.Loading(true))
    }

    @Test
    fun popularShowsDetailsViewModel_testUseCaseInvocation() {
        runTest {
            val mockAccountId = 100
            popularShowDetailsViewModel.getShowDetailsFromDB(mockAccountId)
            verify(fakePopularShowDetailsUseCase).getPopularShowDetails(mockAccountId,dispatcherProvider.default)
        }
    }

    @Test
    fun popularShowsDetailsViewModel_getShowDetailsFromDB() {
        runTest {
            val mockAccountId = 100
            val mockShowObjectDto = mock(ShowDomainModel::class.java)
            Mockito.doReturn(flowOf(mockShowObjectDto)).`when`(fakePopularShowDetailsUseCase)
                .getPopularShowDetails(mockAccountId, dispatcherProvider.io)

            popularShowDetailsViewModel.getShowDetailsFromDB(mockAccountId)

            Assert.assertEquals(
                popularShowDetailsViewModel.data.value,
                State.OnComplete(mockShowObjectDto)
            )
        }

    }
}