package com.imoviedb.app.data.storage.authentication

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserToken")
class UserTokenEntity {

    @ColumnInfo(name ="success")
    var success: Boolean? = null

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "request_token")
    var request_token: String =""

    @ColumnInfo(name ="expires_at")
    var expiresAt: String? = null

    @ColumnInfo(name ="status_code")
    var status_code: String? = null

    @ColumnInfo(name ="status_message")
    var status_message: String? = null



}