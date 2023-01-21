package com.imoviedb.app.domain.popularshows.details.usecase

import com.imoviedb.app.domain.base.BaseDomainTestClass
import com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepository
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GetPopularShowDetailsUseCaseImplTest : BaseDomainTestClass() {

    private lateinit var getPopularShowsUsecase: GetPopularShowDetailsUseCaseImpl

    private val popularShowDetailsRepository: PopularShowDetailsRepository = mock()

    override fun postSetup() {
        getPopularShowsUsecase = GetPopularShowDetailsUseCaseImpl(popularShowDetailsRepository)
    }

    @Test
    fun getPopularShowDetails() {
        runTest {
            // Arrange
            val showIdMock = 1
            val showDomainMock = mock(ShowDomainModel::class.java)
            val stubInput = flowOf(showDomainMock)
            doReturn(stubInput).`when`(popularShowDetailsRepository)
                .getPopularShowDetails(showIdMock)

            //Act
            getPopularShowsUsecase.getPopularShowDetails(showIdMock)
            val result = popularShowDetailsRepository.getPopularShowDetails(showIdMock)

            //Assertion
            assertEquals(stubInput, result)
        }
    }

}