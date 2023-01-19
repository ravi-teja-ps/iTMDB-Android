package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowEntityModelMapper @Inject constructor() :
    Mapper<ShowEntityModel, ShowDomainModel> {

    override fun map(input: ShowEntityModel): ShowDomainModel {
        return ShowDomainModel().apply {
            adult = input.adult
            id = input.id
            title = input.title
            originalLanguage = input.originalLanguage
            originalTitle = input.originalTitle
            overview = input.overview
            posterPath = input.posterPath
            mediaType = input.mediaType
            backdropPath = input.backdrop_path
            popularity = input.popularity
            releaseDate = input.releaseDate
            video = input.video
            voteAverage = input.voteAverage
            voteCount = input.voteCount
            name = input.name
            originalName = input.originalName
            firstAirDate = input.firstAirDate
            insertOrder = input.insertOrder
        }
    }
}