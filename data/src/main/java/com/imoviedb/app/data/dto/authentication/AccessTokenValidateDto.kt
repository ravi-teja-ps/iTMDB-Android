package com.imoviedb.app.data.dto.authentication

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

data class AccessTokenValidateDto(
    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("status_message")
    var statusMessage: String?,

    @SerializedName("success")
    var success: Boolean,

    @SerializedName("request_token")
    var requestToken: String,

    @SerializedName("expires_at")
    var expiresAt: String
) : BaseResponseDto

