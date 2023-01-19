package com.imoviedb.app.data.storage.popularshows

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularShows")
class ShowEntityModel {

    @ColumnInfo(name = "adult")
    var adult: Boolean? = null

    @PrimaryKey(autoGenerate = true)
    var insertOrder: Int = 0

    @ColumnInfo(name = "id")
    var id: Int = -1

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null

    @ColumnInfo(name = "overview")
    var overview: String? = null

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String? = null


    @ColumnInfo(name = "media_type")
    var mediaType: String? = null

    @ColumnInfo(name = "popularity")
    var popularity: Double? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "video")
    var video: Boolean? = null

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = null

    @ColumnInfo(name = "vote_count")
    var voteCount: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "original_name")
    var originalName: String? = null

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null

}