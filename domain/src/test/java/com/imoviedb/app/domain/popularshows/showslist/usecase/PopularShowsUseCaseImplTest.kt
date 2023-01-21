package com.imoviedb.app.domain.popularshows.showslist.usecase

import androidx.paging.PagingData
import com.imoviedb.app.domain.base.BaseDomainTestClass
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class PopularShowsUseCaseImplTest : BaseDomainTestClass() {

    private lateinit var popularShowsUseCaseImpl: PopularShowsUseCaseImpl

    private val popularShowsRepository: PopularShowsRepository = mock()

    override fun postSetup() {
        popularShowsUseCaseImpl = PopularShowsUseCaseImpl(popularShowsRepository)
    }

    @Test
    fun fetchPopularShows() {
        runTest {
            //Arrange
            val mock = mock(ShowDomainModel::class.java)
            val input = MutableStateFlow(PagingData.from(listOf(mock)))
            doReturn(input).`when`(popularShowsRepository).getPopularShows()

            //Act
            val result = popularShowsRepository.getPopularShows()

            //Assert
            assertEquals(result, input)
        }
    }
}