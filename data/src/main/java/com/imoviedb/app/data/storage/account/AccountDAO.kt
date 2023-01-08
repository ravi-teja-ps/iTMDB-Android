package com.imoviedb.app.data.storage.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountDAO {
    @Insert
    fun insertAccountInfo(accountEntity: AccountEntity)

    @Query("delete from Account")
    fun deleteAccount()

    @Query("delete from Account where id = :id")
    fun deleteAccount(id: Int)

    @Query("SELECT * from Account where id = :accountId")
    fun getAccountInfo(accountId : Int) : AccountEntity

    @Query("SELECT * from Account")
    fun getAccountInfo() : AccountEntity

    @Update
    fun updateAccountInfo(accountEntity: AccountEntity)

}