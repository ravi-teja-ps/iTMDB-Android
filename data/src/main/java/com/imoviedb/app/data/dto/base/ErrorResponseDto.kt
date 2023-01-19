package com.imoviedb.app.data.dto.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Add additional exception info if needed to extraData and use that to show details info
data class ErrorResponseDto(val extraData: String = "") : BaseResponseDto() {

    @SerializedName("success")
    @Expose
    var success: Boolean = false
}