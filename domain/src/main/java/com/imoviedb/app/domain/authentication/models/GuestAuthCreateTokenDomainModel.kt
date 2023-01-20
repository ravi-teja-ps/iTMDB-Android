package com.imoviedb.app.domain.authentication.models


import com.imoviedb.app.domain.base.BaseDomainModel

class GuestAuthCreateTokenDomainModel : BaseDomainModel() {

    var success: Boolean = false

    var requestToken: String? = null

    var expiresAt: String? = null
}