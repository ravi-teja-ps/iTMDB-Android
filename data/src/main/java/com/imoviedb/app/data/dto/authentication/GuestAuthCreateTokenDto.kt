package com.imoviedb.app.data.dto.authentication

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

data class GuestAuthCreateTokenDto(
    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("status_message")
    val statusMessage: String?,

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("request_token")
    val requestToken: String?,

    @SerializedName("expires_at")
    val expiresAt: String?
) : BaseResponseDto