package com.imoviedb.app.data.models.authentication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.models.BaseResponseModel

class NewSessionModel : BaseResponseModel(){
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("session_id")
    @Expose
    var session_id: String? = null
}