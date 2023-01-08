package com.imoviedb.app.data.storage.authentication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GuestUserTokenDAO {

    @Query("delete from GuestUserToken")
    fun removeToken()

    @Query("select * from GuestUserToken")
    fun getToken() : GuestUserTokenEntity

    @Insert
    fun saveToken(userTokenInfo : GuestUserTokenEntity)
}