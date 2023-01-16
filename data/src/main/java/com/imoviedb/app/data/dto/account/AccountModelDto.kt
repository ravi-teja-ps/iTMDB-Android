package com.imoviedb.app.data.dto.account

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.imoviedb.app.data.dto.base.BaseResponseDto

class AccountDto : BaseResponseDto() {
    @SerializedName("avatar")
    @Expose
    var avatarDto: AvatarDto? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("include_adult")
    @Expose
    var includeAdult: Boolean? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

}

 class AvatarDto {
    @SerializedName("gravatar")
    @Expose
    var gravatarDto: GravatarDto? = null
}

 class GravatarDto {
    @SerializedName("hash")
    @Expose
    var hash: String? = null
}