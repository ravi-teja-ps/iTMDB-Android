package com.imoviedb.app.data.dto.authentication

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class GuestAuthCreateTokenDto : BaseResponseDto() {
    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("request_token")
    var requestToken: String? = null

    @SerializedName("expires_at")
    var expiresAt: String? = null
}