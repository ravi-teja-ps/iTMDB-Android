package com.imoviedb.app.domain.popularshows.details.usecase

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface GetPopularShowDetailsUseCase {

    suspend fun getPopularShowDetails(id: Int): Flow<ResponseWrapper<ShowDomainModel>>
}