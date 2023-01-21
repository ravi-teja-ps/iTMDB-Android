package com.imoviedb.app.data.storage.authentication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserToken")
data class UserTokenEntity(

    @ColumnInfo(name = "success")
    val success: Boolean,

    @PrimaryKey
    @ColumnInfo(name = "request_token")
    val requestToken: String,

    @ColumnInfo(name = "expires_at")
    val expiresAt: String

)