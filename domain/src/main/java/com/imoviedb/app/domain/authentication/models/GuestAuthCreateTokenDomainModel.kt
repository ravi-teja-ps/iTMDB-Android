package com.imoviedb.app.domain.authentication.models


data class GuestAuthCreateTokenDomainModel(
    val statusCode: Int,
    val statusMessage: String?,
    var success: Boolean,
    val requestToken: String?,
    val expiresAt: String?
)