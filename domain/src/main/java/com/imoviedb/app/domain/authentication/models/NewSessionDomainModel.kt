package com.imoviedb.app.domain.authentication.models

data class NewSessionDomainModel(
    val statusCode: Int,
    val statusMessage: String?,
    val success: Boolean,
    val sessionId: String,
    val expiresAt: String?
)