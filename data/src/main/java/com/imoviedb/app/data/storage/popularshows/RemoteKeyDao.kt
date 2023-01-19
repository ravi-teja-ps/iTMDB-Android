package com.imoviedb.app.data.storage.popularshows

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_key LIMIT 1")
    suspend fun get(): RemoteKey?

    @Query("DELETE FROM remote_key")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM remote_key LIMIT 1")
    fun getKeyFlow(): Flow<RemoteKey?>
}