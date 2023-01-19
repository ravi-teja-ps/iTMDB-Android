package com.imoviedb.app.data.storage.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDAO {
    @Insert
    fun insertAccountInfo(accountEntity: AccountEntity)

    @Query("delete from Account")
    fun deleteAccount()
}