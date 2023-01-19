package com.imoviedb.app.data.storage.authentication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserSessionDAO {

    @Query("delete from UserSession")
    fun removeAllSessions()

    @Query("select * from UserSession")
    fun getSession(): UserSessionEntity

    @Insert
    fun saveSession(userTokenInfo: UserSessionEntity)
}