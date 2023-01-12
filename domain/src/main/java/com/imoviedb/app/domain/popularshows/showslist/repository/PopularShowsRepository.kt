package com.imoviedb.app.domain.popularshows.showslist.repository

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import kotlinx.coroutines.flow.Flow

interface PopularShowsRepository {
    fun getPopularShows(): Flow<PagingData<ShowDomainModel>>
}