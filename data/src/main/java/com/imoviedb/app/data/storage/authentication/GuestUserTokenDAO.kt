package com.imoviedb.app.data.storage.authentication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GuestUserTokenDAO {

    @Query("delete from GuestUserToken")
    fun removeToken()

    @Insert
    fun saveToken(userTokenInfo: GuestUserTokenEntity)
}