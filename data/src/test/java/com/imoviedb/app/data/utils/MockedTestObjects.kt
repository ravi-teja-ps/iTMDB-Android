package com.imoviedb.app.data.utils

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.data.dto.popular.PopularShowsDto
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel

val mockedAccountDto: AccountDto by lazy { getAccountDtoAsMock() }
val mockedShowDto: ShowDto by lazy { getShowDtoAsMock() }
val mockedShowEntity: ShowEntityModel by lazy { getShowEntityMock() }
val mockedPopularShowsDto: PopularShowsDto by lazy { getPopularShowDto() }
val mockedAccessTokenDto: AccessTokenValidateDto by lazy { getAccessTokenDto() }
val mockedAccessTokenValidateDto: AccessTokenValidateDomainModel by lazy { getAccessTokenDomainModelMock() }


private fun getAccountDtoAsMock(): AccountDto {
    return AccountDto(
        id = 1,
        name = "Test",
        includeAdult = false,
        iso31661 = "en",
        iso6391 = "IND",
        username = "max_zs",
        statusCode = -1,
        statusMessage = null,
        avatarDto = null
    )
}


private fun getShowDtoAsMock(): ShowDto {
    return ShowDto(
        adult = false,
        id = 1,
        title = "Avatar Way of Water",
        originalLanguage = "en",
        originalTitle = "Avatar Way of Water",
        overview = "Avatar way of water is a continuation of Avatar 1 ",
        posterPath = "/path/avatar.jpg",
        backdropPath = null,
        mediaType = "movie",
        popularity = 4.9,
        releaseDate = "22-5-21",
        video = false,
        voteAverage = 2.0,
        voteCount = 2,
        name = "Avatar",
        originalName = "Avatar 2",
        firstAirDate = "22-5-21"
    )
}

private fun getShowEntityMock(): ShowEntityModel {
    return ShowEntityModel(
        adult = false,
        id = 1,
        title = "Avatar Way of Water",
        originalLanguage = "en",
        originalTitle = "Avatar Way of Water",
        overview = "Avatar way of water is a continuation of Avatar 1 ",
        posterPath = "/path/avatar.jpg",
        mediaType = "movie",
        popularity = 4.9,
        releaseDate = "22-5-21",
        video = false,
        voteAverage = 2.0,
        voteCount = 2,
        name = "Avatar",
        originalName = "Avatar 2",
        firstAirDate = "22-5-21",
        insertOrder = 1,
        backdrop_path = null
    )
}

private fun getPopularShowDto(): PopularShowsDto {
    return PopularShowsDto(
        page = 1,
        shows = listOf(getShowDtoAsMock()),
        totalPages = 10
    )
}

private fun getAccessTokenDto(): AccessTokenValidateDto {
    return AccessTokenValidateDto(
        success = true,
        requestToken = "aZis",
        expiresAt = "119192939393",
        statusCode = -1,
        statusMessage = null
    )
}

private fun getAccessTokenDomainModelMock(): AccessTokenValidateDomainModel {
    return AccessTokenValidateDomainModel(
        success = false,
        requestToken = "",
        expiresAt = "",
        statusCode = -1,
        statusMessage = "Invalid access token"
    )
}
