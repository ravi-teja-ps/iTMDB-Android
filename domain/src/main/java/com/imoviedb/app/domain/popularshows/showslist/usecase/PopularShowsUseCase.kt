package com.imoviedb.app.domain.popularshows.showslist.usecase

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import kotlinx.coroutines.flow.Flow

interface PopularShowsUseCase {
    suspend fun fetchPopularShows(): Flow<PagingData<ShowDomainModel>>
}

