package com.imoviedb.app.domain.authentication.models

import com.imoviedb.app.domain.base.BaseDomainModel

class GuestAuthCreateTokenDomainModel :BaseDomainModel() {

    var success: Boolean? = null

    var request_token: String? = null

}