package com.imoviedb.app.data.dto.popular

import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class ShowDto : BaseResponseDto() {
    @SerializedName("adult")
    var adult: Boolean? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("media_type")
    var mediaType: String? = null


    @SerializedName("popularity")
    var popularity: Double? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("video")
    var video: Boolean? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("original_name")
    var originalName: String? = null

    @SerializedName("first_air_date")
    var firstAirDate: String? = null
}