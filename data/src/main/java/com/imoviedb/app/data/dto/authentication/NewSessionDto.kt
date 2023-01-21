package com.imoviedb.app.data.dto.authentication

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

data class NewSessionDto(
    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("status_message")
    val statusMessage: String?,

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("session_id")
    val sessionId: String,

    @SerializedName("expires_at")
    val expiresAt: String?
) : BaseResponseDto