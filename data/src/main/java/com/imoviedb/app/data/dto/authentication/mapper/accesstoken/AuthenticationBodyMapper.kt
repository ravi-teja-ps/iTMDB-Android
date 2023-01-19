package com.imoviedb.app.data.dto.authentication.mapper.accesstoken

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.utils.KeyUtils
import com.imoviedb.app.domain.account.model.AuthenticationBody
import javax.inject.Inject

class AuthenticationBodyMapper @Inject constructor() :
    Mapper<AuthenticationBody, HashMap<String, String>> {

    override fun map(input: AuthenticationBody): HashMap<String, String> {
        return HashMap<String, String>().apply {
            with(input) {
                put(KeyUtils.USERNAME_KEY, userName)
                put(KeyUtils.PASSWORD_KEY, password)
                put(KeyUtils.REQUEST_TOKEN_KEY, requestToken)
            }
        }
    }
}