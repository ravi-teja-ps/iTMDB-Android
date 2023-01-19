package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDomainEntityMapper @Inject constructor() :
    Mapper<ShowDomainModel, ShowEntityModel> {

    override fun map(from: ShowDomainModel): ShowEntityModel {
        return ShowEntityModel().apply {
            adult = from.adult
            id = from.id ?: -1
            title = from.title
            originalLanguage = from.originalLanguage
            originalTitle = from.originalTitle
            overview = from.overview
            posterPath = from.posterPath
            backdrop_path = from.backdropPath
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