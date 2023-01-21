package com.imoviedb.app.domain.authentication.models


import com.imoviedb.app.domain.base.BaseDomainModel

data class GuestAuthCreateTokenDomainModel(
    val statusCode: Int,
    val statusMessage: String?,
    var success: Boolean,
    val requestToken: String?,
    val expiresAt: String?
) : BaseDomainModel {
    override fun isResponseSuccessful(): Boolean {
        return statusCode <= 0
    }
}