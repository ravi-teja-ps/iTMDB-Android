package com.imoviedb.app.data.storage.popularshows

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PopularShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(popularShowList: List<ShowEntityModel>)

    @Query("DELETE FROM PopularShows")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM PopularShows")
    fun pagingSource(): PagingSource<Int, ShowEntityModel>

    @Query("SELECT * FROM PopularShows where id= :id")
    suspend fun fetchShowById(id : Int) : ShowEntityModel

}