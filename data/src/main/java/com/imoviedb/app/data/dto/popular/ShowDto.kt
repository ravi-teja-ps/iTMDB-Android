package com.imoviedb.app.data.dto.popular

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.imoviedb.app.data.dto.base.BaseResponseDto

class ShowDto : BaseResponseDto() {
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null



    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("video")
    @Expose
    var video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("original_name")
    @Expose
    var originalName: String? = null

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null
}