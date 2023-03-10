package com.imoviedb.app.data.repository.popularshows.details

import com.imoviedb.app.data.dto.popular.mapper.PopularShowEntityModelMapper
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import com.imoviedb.app.domain.popularshows.details.repository.PopularShowDetailsRepository
import com.imoviedb.common.state.ResponseWrapper
import com.imoviedb.app.data.networking.utils.toResponseWrapper
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularShowDetailsRepositoryImpl @Inject constructor(
    private val popularShowsDao: PopularShowsDao,
    private val entityModelMapper: PopularShowEntityModelMapper,
    private val dispatcherProvider: DispatcherProvider
) : PopularShowDetailsRepository {

    override suspend fun getPopularShowDetails(id: Int) = flow {
        val showDetails = popularShowsDao.fetchShowById(id = id)
        try {
            emit(ResponseWrapper.Success(entityModelMapper.map(showDetails)))
        }catch (exception : Exception){
            emit(exception.toResponseWrapper())
        }
        // Add else{ } for Future enhance the ui by making a new call to
        //fetch show data from network
    }.flowOn(dispatcherProvider.default)
}