package com.imoviedb.app.data.storage.popularshows

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularShows")
data class ShowEntityModel(

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @PrimaryKey(autoGenerate = true)
    val insertOrder: Int = 0,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String?,


    @ColumnInfo(name = "media_type")
    val mediaType: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "video")
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "original_name")
    val originalName: String?,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String?,

    )