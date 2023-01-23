package com.imoviedb.app.data.dto.base

import com.google.gson.annotations.SerializedName

//Add additional exception info if needed to extraData and use that to show details info
data class ErrorResponseDto(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean
) : BaseResponseDto