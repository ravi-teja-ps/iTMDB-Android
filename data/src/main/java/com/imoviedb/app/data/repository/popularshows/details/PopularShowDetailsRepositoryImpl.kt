package com.imoviedb.app.data.repository.popularshows.details

import com.imoviedb.app.data.dto.popular.mapper.PopularShowDomainEntityMapper
import com.imoviedb.app.data.dto.popular.mapper.PopularShowEntityModelMapper
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularShowDetailsRepositoryImpl @Inject constructor(private val popularShowsDao: PopularShowsDao,
                                                           private val entityModelMapper: PopularShowEntityModelMapper
) : PopularShowDetailsRepository {

    override suspend fun getPopularShowDetails(id : Int, dispatcher: CoroutineDispatcher) = flow {
        val showDetails = popularShowsDao.fetchShowById(id = id)
        emit(entityModelMapper.map(showDetails))
        // Add else{ } for Future enhance the ui by making a new call to
        //fetch show data from network
    }.flowOn(dispatcher)

}