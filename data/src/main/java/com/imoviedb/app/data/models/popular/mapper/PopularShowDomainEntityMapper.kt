package com.imoviedb.app.data.models.popular.mapper

import com.imoviedb.app.data.models.popular.Show
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import javax.inject.Inject

class PopularShowDomainEntityMapper @Inject constructor(){

    fun convertModelToDbEntity(show: Show) : ShowEntityModel {
        return ShowEntityModel().apply {
            adult = show.adult
            id = show.id ?: -1
            title = show.title
            originalLanguage = show.originalLanguage
            originalTitle  = show.originalTitle
            overview = show.overview
            posterPath = show.posterPath
            backdrop_path = show.backdrop_path
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

    fun convertEntityToModel(popularShowsEntity:ShowEntityModel) : Show {
        return Show().apply {
            adult = popularShowsEntity.adult
            id = popularShowsEntity.id
            title = popularShowsEntity.title
            originalLanguage = popularShowsEntity.originalLanguage
            originalTitle = popularShowsEntity.originalTitle
            overview = popularShowsEntity.overview
            posterPath = popularShowsEntity.posterPath
            mediaType = popularShowsEntity.mediaType
            backdrop_path = popularShowsEntity.backdrop_path
            popularity = popularShowsEntity.popularity
            releaseDate = popularShowsEntity.releaseDate
            video = popularShowsEntity.video
            voteAverage = popularShowsEntity.voteAverage
            voteCount = popularShowsEntity.voteCount
            name = popularShowsEntity.name
            originalName = popularShowsEntity.originalName
            firstAirDate = popularShowsEntity.firstAirDate
        }
    }
}