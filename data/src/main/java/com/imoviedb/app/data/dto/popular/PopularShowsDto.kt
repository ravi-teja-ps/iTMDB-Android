package com.imoviedb.app.data.dto.popular

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class PopularShowsDto : BaseResponseDto() {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var shows: List<ShowDto>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
}

