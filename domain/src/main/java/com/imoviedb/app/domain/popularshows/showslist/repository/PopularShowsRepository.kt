package com.imoviedb.app.domain.popularshows.showslist.repository

import androidx.paging.PagingData
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import kotlinx.coroutines.flow.Flow

interface PopularShowsRepository {
    fun getPopularShows(): Flow<PagingData<ShowEntityModel>>
}