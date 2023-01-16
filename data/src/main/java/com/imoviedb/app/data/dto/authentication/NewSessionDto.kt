package com.imoviedb.app.data.dto.authentication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class NewSessionDto : BaseResponseDto(){
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("session_id")
    @Expose
    var sessionId: String? = null
}