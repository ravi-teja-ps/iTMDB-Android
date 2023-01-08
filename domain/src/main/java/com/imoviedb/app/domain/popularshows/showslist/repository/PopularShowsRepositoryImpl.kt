package com.imoviedb.app.domain.popularshows.showslist.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.showslist.paging.PopularShowsRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularShowsRepositoryImpl @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val remoteMediator: PopularShowsRemoteMediator,
    private val popularShowsDao: PopularShowsDao
): PopularShowsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularShows(): Flow<PagingData<ShowEntityModel>> =
        Pager(config = PagingConfig(1), remoteMediator = remoteMediator,){
              popularShowsDao.pagingSource()
        }.flow

}