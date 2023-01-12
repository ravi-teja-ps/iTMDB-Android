package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import javax.inject.Inject

class PopularShowDomainEntityMapper @Inject constructor(){

    fun convertDtoToDomainModel(show: ShowDto) : ShowDomainModel {
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

    fun convertModelToDbEntity(show: ShowDomainModel) : ShowEntityModel {
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

    fun convertEntityToModel(popularShowsEntity:ShowEntityModel) : ShowDomainModel {
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