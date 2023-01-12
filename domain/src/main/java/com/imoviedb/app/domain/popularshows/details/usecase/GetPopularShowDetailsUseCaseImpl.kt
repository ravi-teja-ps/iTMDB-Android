package com.imoviedb.app.domain.popularshows.details.usecase

import com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPopularShowDetailsUseCaseImpl @Inject
constructor(private val popularShowDetailsRepository: PopularShowDetailsRepository):GetPopularShowDetailsUseCase {
    override suspend fun getPopularShowDetails(id: Int, dispatcher: CoroutineDispatcher) =
        popularShowDetailsRepository.getPopularShowDetails(id, dispatcher)
}