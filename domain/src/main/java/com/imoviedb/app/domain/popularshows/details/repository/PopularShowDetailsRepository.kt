package com.imoviedb.app.domain.popularshows.details.repository

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface PopularShowDetailsRepository {

    suspend fun getPopularShowDetails(id: Int): Flow<ResponseWrapper<ShowDomainModel>>
}