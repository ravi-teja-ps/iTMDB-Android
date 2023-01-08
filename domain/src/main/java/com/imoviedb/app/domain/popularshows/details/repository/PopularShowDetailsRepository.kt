package com.imoviedb.app.domain.popularshows.details.repository

import com.imoviedb.app.data.models.popular.Show
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface PopularShowDetailsRepository{

    suspend fun getPopularShowDetails(id : Int, dispatcher: CoroutineDispatcher,): Flow<Show>
}