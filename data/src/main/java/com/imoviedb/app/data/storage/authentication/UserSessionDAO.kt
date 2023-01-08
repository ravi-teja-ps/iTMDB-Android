package com.imoviedb.app.data.storage.authentication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserSessionDAO {
    @Delete
    fun removeSession(userTokenInfo : UserSessionEntity)

    @Query("delete from UserSession")
    fun removeAllSessions()

    @Query("select * from UserSession")
    fun getSession() : UserSessionEntity

    @Insert
    fun saveSession(userTokenInfo : UserSessionEntity)
}