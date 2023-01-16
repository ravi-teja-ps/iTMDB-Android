package com.imoviedb.app.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class BaseResponseDto {
    @SerializedName("status_code")
    @Expose
    var statusCode: Int = -1

    @SerializedName("status_message")
    @Expose
    var statusMessage: String? = null
}