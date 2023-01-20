package com.imoviedb.app.data.storage.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Account")

class AccountEntity {
    @ColumnInfo(name = "avatar")
    var avatarHash: String? = null

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = -1

    @ColumnInfo(name = "iso_639_1")
    var iso6391: String? = ""

    @ColumnInfo(name = "iso_3166_1")
    var iso31661: String? = ""

    @ColumnInfo(name = "name")
    var name: String? = ""

    @ColumnInfo(name = "include_adult")

    var includeAdult: Boolean = false

    @ColumnInfo(name = "username")
    var username: String? = ""

    @ColumnInfo(name = "status_code")
    var statusCode: Int = -1

    @ColumnInfo(name = "status_message")
    var statusMessage: String? = ""
}