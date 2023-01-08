package com.imoviedb.app.data.models.authentication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.models.BaseResponseModel

class AccessTokenValidateModel :BaseResponseModel(){
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("request_token")
    @Expose
    var request_token: String? = null

    @SerializedName("expires_at")
    @Expose
    var expiresAt: String? = null
}