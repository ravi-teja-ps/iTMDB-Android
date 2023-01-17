package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDtoDomainMapper @Inject constructor() : Mapper<ShowDto,ShowDomainModel> {

    override fun map(show: ShowDto) : ShowDomainModel {
        return ShowDomainModel().apply {
            adult = show.adult
            id = show.id ?: -1
            title = show.title
            originalLanguage = show.originalLanguage
            originalTitle  = show.originalTitle
            overview = show.overview
            posterPath = show.posterPath
            backdropPath = show.backdrop_path
            mediaType = show.mediaType
            popularity= show.popularity
            releaseDate = show.releaseDate
            video = show.video
            voteAverage = show.voteAverage
            voteCount = show.voteCount
            name = show.name
            originalName = show.originalName
            firstAirDate = show.firstAirDate
        }
    }
}