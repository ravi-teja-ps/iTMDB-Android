package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowEntityModelMapper @Inject constructor(): Mapper<ShowEntityModel,ShowDomainModel> {

    override fun map(popularShowsEntity: ShowEntityModel) : ShowDomainModel {
        return ShowDomainModel().apply {
            adult = popularShowsEntity.adult
            id = popularShowsEntity.id
            title = popularShowsEntity.title
            originalLanguage = popularShowsEntity.originalLanguage
            originalTitle = popularShowsEntity.originalTitle
            overview = popularShowsEntity.overview
            posterPath = popularShowsEntity.posterPath
            mediaType = popularShowsEntity.mediaType
            backdropPath = popularShowsEntity.backdrop_path
            popularity = popularShowsEntity.popularity
            releaseDate = popularShowsEntity.releaseDate
            video = popularShowsEntity.video
            voteAverage = popularShowsEntity.voteAverage
            voteCount = popularShowsEntity.voteCount
            name = popularShowsEntity.name
            originalName = popularShowsEntity.originalName
            firstAirDate = popularShowsEntity.firstAirDate
            insertOrder = popularShowsEntity.insertOrder
        }
    }
}