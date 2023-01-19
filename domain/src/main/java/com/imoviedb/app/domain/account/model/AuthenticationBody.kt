package com.imoviedb.app.domain.account.model

data class AuthenticationBody(var userName : String,
                              var password : String,
                              var request_token: String)