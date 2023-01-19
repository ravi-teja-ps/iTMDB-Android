package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowEntityModelMapper @Inject constructor() :
    Mapper<ShowEntityModel, ShowDomainModel> {

    override fun map(from: ShowEntityModel): ShowDomainModel {
        return ShowDomainModel().apply {
            adult = from.adult
            id = from.id
            title = from.title
            originalLanguage = from.originalLanguage
            originalTitle = from.originalTitle
            overview = from.overview
            posterPath = from.posterPath
            mediaType = from.mediaType
            backdropPath = from.backdrop_path
            popularity = from.popularity
            releaseDate = from.releaseDate
            video = from.video
            voteAverage = from.voteAverage
            voteCount = from.voteCount
            name = from.name
            originalName = from.originalName
            firstAirDate = from.firstAirDate
            insertOrder = from.insertOrder
        }
    }
}