package com.imoviedb.app.ui.popularshows

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCase
import com.imoviedb.app.ui.mockedPopularShowsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePopularShowsUseCase : PopularShowsUseCase {
    private val flow = MutableStateFlow(PagingData.from(listOf(mockedPopularShowsModel)))

    override suspend fun fetchPopularShows(): Flow<PagingData<ShowDomainModel>> {
        return flow
    }
}