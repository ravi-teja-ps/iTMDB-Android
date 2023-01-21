package com.imoviedb.app.ui

import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel

val mockedPopularShowsModel : ShowDomainModel by lazy { getShowDomainMock() }
val mockedSessionDomainModel: NewSessionDomainModel by lazy { getSessionDomainModelMock() }
val mockedAccessTokenDomainModel: AccessTokenValidateDomainModel by lazy { getAccessTokenDomainModelMock() }
val mockedGuestAuthTokenDomainModel: GuestAuthCreateTokenDomainModel by lazy { getGuestAuthTokenModelMock() }
private fun getShowDomainMock() : ShowDomainModel {
    return ShowDomainModel(
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
        backdropPath = null
    )
}

private fun getSessionDomainModelMock() : NewSessionDomainModel {
    return NewSessionDomainModel(
        sessionId = "ZiavZdgasdasd",
        success = true,
        statusCode = -1,
        statusMessage = null,
        expiresAt = null
    )
}

private fun getAccessTokenDomainModelMock() : AccessTokenValidateDomainModel {
    return AccessTokenValidateDomainModel(
        success = false,
        requestToken = "",
        expiresAt = "",
        statusCode = -1,
        statusMessage = "Invalid access token"
    )
}

private fun getGuestAuthTokenModelMock() : GuestAuthCreateTokenDomainModel {
    return GuestAuthCreateTokenDomainModel(
        requestToken = "Ajada2",
        expiresAt = null,
        statusCode = -1,
        success = true,
        statusMessage = null
    )
}