package com.imoviedb.app.domain.popularshows.models

import com.imoviedb.app.domain.base.BaseDomainModel

class ShowDomainModel : BaseDomainModel() {

    var adult: Boolean? = null

    var insertOrder: Int = 0

    var id: Int? = null

    var title: String? = null

    var originalLanguage: String? = null

    var originalTitle: String? = null

    var overview: String? = null

    var posterPath: String? = null

    var backdropPath: String? = null

    var mediaType: String? = null

    var popularity: Double? = null

    var releaseDate: String? = null

    var video: Boolean? = null

    var voteAverage: Double? = null

    var voteCount: Int? = null

    var name: String? = null

    var originalName: String? = null

    var firstAirDate: String? = null


}