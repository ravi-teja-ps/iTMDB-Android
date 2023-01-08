package com.imoviedb.app.ui.popularshows

import androidx.paging.PagingData
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepository
import com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePopularShowsUseCase(private val popularShowsRepository: PopularShowsRepository,dataType: Int) : PopularShowsUseCase {
     private val flow = MutableStateFlow(PagingData.from(listOf(ShowEntityModel())))
    suspend fun emit(value: PagingData<ShowEntityModel>) = flow.emit(value)

    override suspend fun fetchPopularShows(): Flow<PagingData<ShowEntityModel>> {
        return flow
    }
}