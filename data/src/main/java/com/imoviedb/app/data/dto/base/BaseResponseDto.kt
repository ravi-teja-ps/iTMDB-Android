package com.imoviedb.app.data.dto.base

import com.google.gson.annotations.SerializedName

abstract class BaseResponseDto {
    @SerializedName("status_code")
    var statusCode: Int = -1

    @SerializedName("status_message")
    var statusMessage: String? = null
}