package com.imoviedb.app.data.storage.authentication

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GuestUserToken")
class GuestUserTokenEntity {

    @ColumnInfo(name ="success")
    var success: Boolean? = null

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "request_token")
    var requestToken: String =""

    @ColumnInfo(name ="expires_at")
    var expiresAt: String? = null

    @ColumnInfo(name ="status_code")
    var status_code: Int = -1

    @ColumnInfo(name ="status_message")
    var status_message: String? = null

}