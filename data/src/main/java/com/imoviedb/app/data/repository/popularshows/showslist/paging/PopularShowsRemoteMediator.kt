package com.imoviedb.app.data.repository.popularshows.showslist.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.data.dto.popular.mapper.PopularShowDomainEntityMapper
import com.imoviedb.app.data.dto.popular.mapper.PopularShowDtoDomainMapper
import com.imoviedb.app.data.networking.apiservice.PopularShowService
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.storage.popularshows.RemoteKey
import com.imoviedb.app.data.storage.popularshows.RemoteKeyDao
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.data.storage.utils.DBTransactionHandler
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PopularShowsRemoteMediator @Inject constructor(
    private val popularShowService: PopularShowService,
    private val popularShowsDao: PopularShowsDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val dbTransaction: DBTransactionHandler,
    private val popularShowDomainEntityMapper: PopularShowDomainEntityMapper,
    private val popularShowDtoDomainMapper: PopularShowDtoDomainMapper

) : RemoteMediator<Int, ShowEntityModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ShowEntityModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    popularShowsDao.deleteAll()
                    remoteKeyDao.deleteAll()

                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    remoteKeyDao.get()?.let {
                        if (it.currentPage == it.lastPage) {
                            return MediatorResult.Success(true)
                        }
                        it.currentPage.plus(1)
                    }
                }
            }

            loadKey?.let {
                val response = popularShowService.popularShows(page = it)
                response.body()?.let { popularShowModel ->
                    popularShowModel.page?.let { currentPage: Int ->
                        //writing to DB under queuing by Room db for synchronization
                        dbTransaction.executeTransaction {
                            remoteKeyDao.insertOrReplace(
                                RemoteKey(
                                    currentPage = currentPage,
                                    lastPage = popularShowModel.totalPages!!
                                )
                            )
                            val popularShowEntityList = mutableListOf<ShowEntityModel>()
                            response.body()?.shows?.map { show: ShowDto ->
                                val domainModel = popularShowDtoDomainMapper.map(show)
                                popularShowEntityList.add(
                                    popularShowDomainEntityMapper.map(domainModel)
                                )
                            }
                            popularShowsDao.insertAll(popularShowEntityList)
                        }
                    }
                }
            }

            MediatorResult.Success(false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}