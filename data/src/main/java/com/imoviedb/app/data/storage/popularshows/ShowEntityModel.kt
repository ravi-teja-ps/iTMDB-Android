package com.imoviedb.app.data.storage.popularshows

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularShows")
class ShowEntityModel {

    @ColumnInfo(name = "adult")
    var adult: Boolean = false

    @PrimaryKey(autoGenerate = true)
    var insertOrder: Int = 0

    @ColumnInfo(name = "id")
    var id: Int = -1

    @ColumnInfo(name = "title")
    var title: String? = ""

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = ""

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = ""

    @ColumnInfo(name = "overview")
    var overview: String? = ""

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String? = null


    @ColumnInfo(name = "media_type")
    var mediaType: String? = ""

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = ""

    @ColumnInfo(name = "video")
    var video: Boolean = false

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0

    @ColumnInfo(name = "vote_count")
    var voteCount: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = ""

    @ColumnInfo(name = "original_name")
    var originalName: String? = ""

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = ""

}