package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDtoDomainMapper @Inject constructor() : Mapper<ShowDto, ShowDomainModel> {

    override fun map(input: ShowDto): ShowDomainModel {
        return ShowDomainModel(
            adult = input.adult,
            id = input.id,
            title = input.title,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            mediaType = input.mediaType,
            popularity = input.popularity,
            releaseDate = input.releaseDate,
            video = input.video,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            name = input.name,
            originalName = input.originalName,
            firstAirDate = input.firstAirDate,
            insertOrder = -1
        )
    }
}