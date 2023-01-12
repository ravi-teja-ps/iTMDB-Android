package com.imoviedb.app.domain.account.model

data class AuthenticationBody(var userName : String,
                              var password : String,
                              var request_token: String) {

    fun asMap(): HashMap<String, String> {
        return HashMap<String, String>().apply {
            put("username" ,userName)
            put("password",password)
            put("request_token",request_token)
        }
    }
}