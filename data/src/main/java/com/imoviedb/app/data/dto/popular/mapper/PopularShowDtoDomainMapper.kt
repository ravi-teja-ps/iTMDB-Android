package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDtoDomainMapper @Inject constructor() : Mapper<ShowDto, ShowDomainModel> {

    override fun map(from: ShowDto): ShowDomainModel {
        return ShowDomainModel().apply {
            adult = from.adult
            id = from.id ?: -1
            title = from.title
            originalLanguage = from.originalLanguage
            originalTitle = from.originalTitle
            overview = from.overview
            posterPath = from.posterPath
            backdropPath = from.backdropPath
            mediaType = from.mediaType
            popularity = from.popularity
            releaseDate = from.releaseDate
            video = from.video
            voteAverage = from.voteAverage
            voteCount = from.voteCount
            name = from.name
            originalName = from.originalName
            firstAirDate = from.firstAirDate
        }
    }
}