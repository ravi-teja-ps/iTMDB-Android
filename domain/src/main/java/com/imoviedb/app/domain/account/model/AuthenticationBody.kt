package com.imoviedb.app.domain.account.model

data class AuthenticationBody(
    val userName: String,
    val password: String,
    val requestToken: String
)