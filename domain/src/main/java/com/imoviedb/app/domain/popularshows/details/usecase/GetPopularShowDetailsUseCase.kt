package com.imoviedb.app.domain.popularshows.details.usecase

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface GetPopularShowDetailsUseCase {

    suspend fun getPopularShowDetails(id: Int, dispatcher: CoroutineDispatcher): Flow<ShowDomainModel>
}