package com.imoviedb.app.data.dto.popular

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class PopularShowsDto : BaseResponseDto() {
    @SerializedName("page")
    var page: Int? = null

    @SerializedName("results")
    var shows: List<ShowDto>? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null
}

