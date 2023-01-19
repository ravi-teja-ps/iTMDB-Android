package com.imoviedb.app.data.storage.authentication

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserTokenDAO {

    @Insert
    fun saveToken(userTokenInfo: UserTokenEntity)
}