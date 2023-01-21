package com.imoviedb.app.data.repository.popularshows.showslist

import androidx.paging.*
import com.imoviedb.app.data.base.BaseDataTestClass
import com.imoviedb.app.data.dto.popular.mapper.PopularShowEntityModelMapper
import com.imoviedb.app.data.repository.popularshows.showslist.paging.PopularShowsRemoteMediator
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.data.utils.mockedShowEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalPagingApi::class)
class PopularShowsRepositoryImplTest : BaseDataTestClass() {

    private val remoteMediator: PopularShowsRemoteMediator = mock()
    private val popularShowsDao: PopularShowsDao = mock()
    private val mapper: PopularShowEntityModelMapper = mock()
    private lateinit var popularShowsRepo: PopularShowsRepositoryImpl

    override fun onPostSetup() {
        popularShowsRepo =
            PopularShowsRepositoryImpl(remoteMediator, popularShowsDao, mapper, dispatcherProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPopularShows() {
        runTest {
            //arrange
            val mockPaging = listOf(mockedShowEntity)
            val mockPagingDataFlow = PagingData.from(mockPaging)

            //Act . Transformation assertion
            mockPagingDataFlow.map {
                //Assertion
                assert(it is ShowEntityModel)
            }
        }
    }
}