package com.imoviedb.app.domain.popularshows.showslist.repository

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface PopularShowsRepository {
    fun getPopularShows(coroutineDispatcher:CoroutineDispatcher): Flow<PagingData<ShowDomainModel>>
}