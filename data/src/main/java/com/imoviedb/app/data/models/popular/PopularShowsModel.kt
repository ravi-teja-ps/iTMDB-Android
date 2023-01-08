package com.imoviedb.app.data.models.popular

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.models.BaseResponseModel

class PopularShowsModel : BaseResponseModel() {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var shows: List<Show>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
}

