package com.imoviedb.app.domain.popularshows.showslist.paging

 import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
 import com.imoviedb.app.data.networking.apiservice.PopularShowService
import com.imoviedb.app.data.storage.AppDatabase
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
 import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
 import com.imoviedb.app.data.storage.popularshows.RemoteKey
import com.imoviedb.app.data.storage.popularshows.RemoteKeyDao
 import com.imoviedb.app.domain.popularshows.showslist.mapper.convertModelToDbEntity
 import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PopularShowsRemoteMediator @Inject constructor(private val popularShowService: PopularShowService,
                                                     private val popularShowsDao: PopularShowsDao,
                                                     private val remoteKeyDao: RemoteKeyDao,
                                                     private val appDatabase: AppDatabase)
    : RemoteMediator<Int, ShowEntityModel>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, ShowEntityModel>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    popularShowsDao.deleteAll()
                    remoteKeyDao.deleteAll()

                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val remoteKey = remoteKeyDao.get()!!

                    if (remoteKey.currentPage == remoteKey.lastPage) {
                        return MediatorResult.Success(true)
                    }

                    remoteKey.currentPage.plus(1)
                }
            }

            val response = popularShowService.popularShows(page = loadKey)
            appDatabase.withTransaction {
                remoteKeyDao.insertOrReplace(
                    RemoteKey(
                        currentPage = response.body()!!.page!!, lastPage = response.body()!!.totalPages!!))

                val popularShowEntityList = mutableListOf <ShowEntityModel>()
                response.body()?.shows?.map {
                    popularShowEntityList.add(it.convertModelToDbEntity())
                }
                 popularShowsDao.insertAll(popularShowEntityList)
            }

            MediatorResult.Success(false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}