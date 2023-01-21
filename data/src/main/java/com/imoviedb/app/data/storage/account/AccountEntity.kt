package com.imoviedb.app.data.storage.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")

data class AccountEntity(
    @ColumnInfo(name = "avatar")
    val avatarHash: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "iso_639_1")
    val iso6391: String?,

    @ColumnInfo(name = "iso_3166_1")
    val iso31661: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "include_adult")

    val includeAdult: Boolean,

    @ColumnInfo(name = "username")
    val username: String?,

    @ColumnInfo(name = "status_code")
    val statusCode: Int,

    @ColumnInfo(name = "status_message")
    val statusMessage: String?
)