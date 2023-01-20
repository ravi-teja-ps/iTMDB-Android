package com.imoviedb.app.data.repository.popularshows.showslist.paging

import androidx.paging.*
import com.imoviedb.app.data.base.BaseDataTestClass
import com.imoviedb.app.data.dto.popular.mapper.PopularShowDomainEntityMapper
import com.imoviedb.app.data.dto.popular.mapper.PopularShowDtoDomainMapper
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.storage.popularshows.RemoteKeyDao
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.data.storage.utils.DBTransactionHandler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalPagingApi::class)
class PopularShowsRemoteMediatorTest : BaseDataTestClass() {

    private val popularShowService: FakePopularShowService = mock()
    private val popularShowsDao: PopularShowsDao = mock()
    private val remoteKeyDao: RemoteKeyDao = mock()
    private val dbTransaction: DBTransactionHandler = mock()
    private val popularShowDomainEntityMapper: PopularShowDomainEntityMapper = mock()
    private val popularShowDtoDomainMapper: PopularShowDtoDomainMapper = mock()

    private lateinit var remoteMediator: PopularShowsRemoteMediator
    override fun onPostSetup() {

        remoteMediator = PopularShowsRemoteMediator(
            popularShowService,
            popularShowsDao,
            remoteKeyDao,
            dbTransaction,
            popularShowDomainEntityMapper,
            popularShowDtoDomainMapper
        )

    }

    @Test
    fun `test remote mediator initial result`() = runTest {
        // Arrange
        val pagingState =
            PagingState<Int, ShowEntityModel>(listOf(), null, PagingConfig(PAGE_SIZE), PAGE_SIZE)
        remoteMediator.initialize()
        val result = remoteMediator.load(LoadType.APPEND, pagingState)

        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}