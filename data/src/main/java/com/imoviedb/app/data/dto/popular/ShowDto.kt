package com.imoviedb.app.data.dto.popular

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class ShowDto : BaseResponseDto() {
    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("id")
    var id: Int  = -1

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("original_language")
    var originalLanguage: String? = ""

    @SerializedName("original_title")
    var originalTitle: String? = ""

    @SerializedName("overview")
    var overview: String? = ""

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("media_type")
    var mediaType: String? = ""


    @SerializedName("popularity")
    var popularity: Double = 0.0

    @SerializedName("release_date")
    var releaseDate: String? = ""

    @SerializedName("video")
    var video: Boolean = false

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("original_name")
    var originalName: String? = ""

    @SerializedName("first_air_date")
    var firstAirDate: String? = ""
}