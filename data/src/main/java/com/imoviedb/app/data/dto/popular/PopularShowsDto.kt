package com.imoviedb.app.data.dto.popular

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

data class PopularShowsDto(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val shows: List<ShowDto> = emptyList(),

    @SerializedName("total_pages")
    val totalPages: Int
) : BaseResponseDto

