package com.imoviedb.app.domain.popularshows.showslist.usecase

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface PopularShowsUseCase {
    suspend fun  fetchPopularShows(coroutineDispatcher: CoroutineDispatcher) : Flow<PagingData<ShowDomainModel>>
}

