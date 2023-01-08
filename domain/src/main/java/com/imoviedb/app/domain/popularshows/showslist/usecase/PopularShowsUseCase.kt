package com.imoviedb.app.domain.popularshows.showslist.usecase

import androidx.paging.PagingData
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import kotlinx.coroutines.flow.Flow

interface PopularShowsUseCase {
    suspend fun  fetchPopularShows() : Flow<PagingData<ShowEntityModel>>
}

