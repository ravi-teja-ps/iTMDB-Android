package com.imoviedb.app.ui.popularshows

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePopularShowsUseCase() : PopularShowsUseCase {
     private val flow = MutableStateFlow(PagingData.from(listOf(ShowDomainModel())))

    override suspend fun fetchPopularShows(coroutineDispatcher: CoroutineDispatcher): Flow<PagingData<ShowDomainModel>> {
        return flow
    }
}