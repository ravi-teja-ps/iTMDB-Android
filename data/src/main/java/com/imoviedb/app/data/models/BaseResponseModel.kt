package com.imoviedb.app.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class BaseResponseModel {
    @SerializedName("status_code")
    @Expose
    var status_code: String? = null

    @SerializedName("status_message")
    @Expose
    var status_message: String? = null
}