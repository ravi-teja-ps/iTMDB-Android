package com.imoviedb.app.domain.popularshows.models

import com.imoviedb.app.domain.base.BaseDomainModel

data class ShowDomainModel(
    val adult: Boolean,
    val insertOrder: Int,
    val id: Int,
    val title: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val mediaType: String?,
    val popularity: Double,
    val releaseDate: String?,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val name: String?,
    val originalName: String?,
    val firstAirDate: String?,
) : BaseDomainModel {
    override fun isResponseSuccessful(): Boolean {
        return false //Not applicable for list item model.
    }
}