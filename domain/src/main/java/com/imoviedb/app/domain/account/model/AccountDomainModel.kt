package com.imoviedb.app.domain.account.model

import com.imoviedb.app.domain.base.BaseDomainModel

data class AccountDomainModel(
    val statusCode: Int,
    val statusMessage: String?,
    val avatarHash: String?,
    val id: Int,
    val iso6391: String?,
    val iso31661: String?,
    val name: String?,
    val includeAdult: Boolean,
    val username: String?
) : BaseDomainModel {
    override fun isResponseSuccessful(): Boolean {
        return statusCode <= 0
    }
}



