package com.imoviedb.app.domain.popularshows.details.repository

import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.domain.popularshows.showslist.mapper.convertEntityToModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularShowDetailsRepositoryImpl @Inject constructor(private val popularShowsDao: PopularShowsDao) :PopularShowDetailsRepository {

    override suspend fun getPopularShowDetails(id : Int, dispatcher: CoroutineDispatcher,) = flow<Show> {
        val showDetails = popularShowsDao.fetchShowById(id= id)
        if(showDetails == null){
            //Make a network call please
        }
        else{
            emit(showDetails.convertEntityToModel())
        }
    }.flowOn(dispatcher)

}