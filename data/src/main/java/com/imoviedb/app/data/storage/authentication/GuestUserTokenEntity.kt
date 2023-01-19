package com.imoviedb.app.data.storage.authentication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GuestUserToken")
class GuestUserTokenEntity {

    @ColumnInfo(name = "success")
    var success: Boolean? = null

    @PrimaryKey
    @ColumnInfo(name = "request_token")
    var requestToken: String = ""

    @ColumnInfo(name = "expires_at")
    var expiresAt: String? = null

    @ColumnInfo(name = "status_code")
    var statusCode: Int = -1

    @ColumnInfo(name = "status_message")
    var statusMessage: String? = null

}