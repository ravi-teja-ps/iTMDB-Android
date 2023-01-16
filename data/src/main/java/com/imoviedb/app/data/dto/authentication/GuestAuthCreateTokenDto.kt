package com.imoviedb.app.data.dto.authentication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class GuestAuthCreateTokenDto : BaseResponseDto(){
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("request_token")
    @Expose
    var requestToken: String? = null

    @SerializedName("expires_at")
    @Expose
    var expiresAt: String? = null
}