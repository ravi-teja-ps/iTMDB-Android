package com.imoviedb.app.domain.authentication.models

import com.imoviedb.app.domain.base.BaseDomainModel

class NewSessionDomainModel : BaseDomainModel() {
    var success: Boolean? = null
    var sessionId: String? = null
}