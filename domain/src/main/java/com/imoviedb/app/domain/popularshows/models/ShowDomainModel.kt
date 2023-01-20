package com.imoviedb.app.domain.popularshows.models

import com.imoviedb.app.domain.base.BaseDomainModel

class ShowDomainModel : BaseDomainModel() {

    var adult: Boolean = false

    var insertOrder: Int = 0

    var id: Int = -1

    var title: String? = ""

    var originalLanguage: String? = ""

    var originalTitle: String? = ""

    var overview: String? = ""

    var posterPath: String? = null

    var backdropPath: String? = null

    var mediaType: String? = ""

    var popularity: Double = 0.0

    var releaseDate: String? = ""

    var video: Boolean  = false

    var voteAverage: Double = 0.0

    var voteCount: Int = 0

    var name: String? = ""

    var originalName: String? = ""

    var firstAirDate: String? = ""


}