package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDomainEntityMapper @Inject constructor(): Mapper<ShowDomainModel,ShowEntityModel>{

    override fun map(show: ShowDomainModel) : ShowEntityModel {
        return ShowEntityModel().apply {
            adult = show.adult
            id = show.id ?: -1
            title = show.title
            originalLanguage = show.originalLanguage
            originalTitle  = show.originalTitle
            overview = show.overview
            posterPath = show.posterPath
            backdrop_path = show.backdropPath
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