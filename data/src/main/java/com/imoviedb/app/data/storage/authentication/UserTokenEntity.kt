package com.imoviedb.app.data.storage.authentication

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserToken")
class UserTokenEntity {

    @ColumnInfo(name = "success")
    var success: Boolean? = null

    @PrimaryKey
    @ColumnInfo(name = "request_token")
    var requestToken: String = ""

    @ColumnInfo(name = "expires_at")
    var expiresAt: String? = null

}