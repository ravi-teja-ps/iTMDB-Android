package com.imoviedb.app.data.storage.authentication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserTokenDAO {

    @Delete
    fun removeToken(userTokenInfo : UserTokenEntity)

    @Query("select * from UserToken")
    fun getToken() : UserTokenEntity

    @Insert
    fun saveToken(userTokenInfo : UserTokenEntity)
}