package com.imoviedb.app.domain.authentication.models

data class AccessTokenValidateDomainModel(
    val statusCode: Int,
    val statusMessage: String?,
    val success: Boolean,
    val requestToken: String,
    val expiresAt: String
)