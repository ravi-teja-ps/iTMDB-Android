package com.imoviedb.app.data.dto.account

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class AccountDto : BaseResponseDto() {
    @SerializedName("avatar")
    var avatarDto: AvatarDto? = null

    @SerializedName("id")
    var id: Int = -1

    @SerializedName("iso_639_1")
    var iso6391: String? = ""

    @SerializedName("iso_3166_1")
    var iso31661: String? = ""

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("include_adult")
    var includeAdult: Boolean = false

    @SerializedName("username")
    var username: String? = ""
}

class AvatarDto {
    @SerializedName("gravatar")
    var gravatarDto: GravatarDto? = null
}

class GravatarDto {
    @SerializedName("hash")
    var hash: String? = null
}