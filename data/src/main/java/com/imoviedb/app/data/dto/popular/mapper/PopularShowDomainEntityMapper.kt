package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDomainEntityMapper @Inject constructor() :
    Mapper<ShowDomainModel, ShowEntityModel> {

    override fun map(input: ShowDomainModel): ShowEntityModel {
        return ShowEntityModel().apply {
            adult = input.adult
            id = input.id
            title = input.title
            originalLanguage = input.originalLanguage
            originalTitle = input.originalTitle
            overview = input.overview
            posterPath = input.posterPath
            backdrop_path = input.backdropPath
            mediaType = input.mediaType
            popularity = input.popularity
            releaseDate = input.releaseDate
            video = input.video
            voteAverage = input.voteAverage
            voteCount = input.voteCount
            name = input.name
            originalName = input.originalName
            firstAirDate = input.firstAirDate
        }
    }
}