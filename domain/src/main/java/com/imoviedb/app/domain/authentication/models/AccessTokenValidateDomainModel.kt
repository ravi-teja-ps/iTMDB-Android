package com.imoviedb.app.domain.authentication.models

import com.imoviedb.app.domain.base.BaseDomainModel

class AccessTokenValidateDomainModel : BaseDomainModel() {

    var success: Boolean? = null

    var requestToken: String? = null

    var expiresAt: String? = null
}