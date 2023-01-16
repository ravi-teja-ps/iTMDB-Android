package com.imoviedb.app.data.repository.popularshows.showslist

import androidx.paging.*
import com.imoviedb.app.data.dto.popular.mapper.PopularShowEntityModelMapper
import com.imoviedb.app.data.repository.popularshows.showslist.paging.PopularShowsRemoteMediator
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularShowsRepositoryImpl @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val remoteMediator: PopularShowsRemoteMediator,
    private val popularShowsDao: PopularShowsDao,
    private val mapper: PopularShowEntityModelMapper
): PopularShowsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularShows(coroutineDispatcher:CoroutineDispatcher) =
         Pager(config = PagingConfig(1), remoteMediator = remoteMediator, pagingSourceFactory = {
            popularShowsDao.pagingSource()
        })  .flow.map {
            it.map {entity->
                mapper.map(entity)
            }
        }.flowOn(coroutineDispatcher)


}