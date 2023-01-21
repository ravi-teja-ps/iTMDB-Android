package com.imoviedb.app.data.dto.account

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

data class AccountDto(

    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("status_message")
    val statusMessage: String?,

    @SerializedName("avatar")
    val avatarDto: AvatarDto?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("iso_639_1")
    val iso6391: String?,

    @SerializedName("iso_3166_1")
    val iso31661: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("include_adult")
    val includeAdult: Boolean,

    @SerializedName("username")
    val username: String?,
) : BaseResponseDto

data class AvatarDto(
    @SerializedName("gravatar")
    val gravatarDto: GravatarDto?
)

data class GravatarDto(
    @SerializedName("hash")
    val hash: String?
)