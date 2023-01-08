package com.imoviedb.app.data.storage.popularshows

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKey(

    @PrimaryKey
    val id: Int = 1,

    @ColumnInfo(name = "current_page")
    val currentPage: Int,

    @ColumnInfo(name ="last_page")
    val lastPage: Int
)