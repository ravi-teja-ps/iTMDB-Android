package com.imoviedb.app.data.models.account

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.imoviedb.app.data.models.BaseResponseModel

class AccountModel : BaseResponseModel() {
    @SerializedName("avatar")
    @Expose
    var avatar: Avatar? = null

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

 class Avatar {
    @SerializedName("gravatar")
    @Expose
    var gravatar: Gravatar? = null
}

 class Gravatar {
    @SerializedName("hash")
    @Expose
    var hash: String? = null
}