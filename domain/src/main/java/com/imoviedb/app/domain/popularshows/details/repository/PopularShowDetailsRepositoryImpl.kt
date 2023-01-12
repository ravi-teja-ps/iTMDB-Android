package com.imoviedb.app.domain.popularshows.details.repository

import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.models.popular.mapper.PopularShowDomainEntityMapper
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularShowDetailsRepositoryImpl @Inject constructor(private val popularShowsDao: PopularShowsDao,
                                                           private val popularShowDomainEntityMapper: PopularShowDomainEntityMapper) :PopularShowDetailsRepository {

    override suspend fun getPopularShowDetails(id : Int, dispatcher: CoroutineDispatcher,) = flow<Show> {
        val showDetails = popularShowsDao.fetchShowById(id= id)
        emit(popularShowDomainEntityMapper.convertEntityToModel(showDetails))
        // Add else{ } for Future enhance the ui by making a new call to
        //fetch show data from network
    }.flowOn(dispatcher)

}