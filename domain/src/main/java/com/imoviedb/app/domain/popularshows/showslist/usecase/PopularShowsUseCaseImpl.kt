package com.imoviedb.app.domain.popularshows.showslist.usecase

import androidx.paging.PagingData
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.domain.popularshows.showslist.repository.PopularShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularShowsUseCaseImpl @Inject constructor(private val popularShowsRepository: PopularShowsRepository) :PopularShowsUseCase{
    override suspend fun fetchPopularShows(coroutineDispatcher: CoroutineDispatcher): Flow<PagingData<ShowDomainModel>>  = popularShowsRepository.getPopularShows(coroutineDispatcher)
}
