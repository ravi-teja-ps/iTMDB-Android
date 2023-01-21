package com.imoviedb.app.data.storage.authentication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserSession")
data class UserSessionEntity(

    @ColumnInfo(name = "success")
    val success: Boolean?,

    @PrimaryKey
    @ColumnInfo(name = "session_id")
    val sessionId: String,

    @ColumnInfo(name = "expires_at")
    val expiresAt: String?,

    @ColumnInfo(name = "status_code")
    val statusCode: Int,

    @ColumnInfo(name = "status_message")
    val statusMessage: String?
)