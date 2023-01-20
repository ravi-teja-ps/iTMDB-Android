package com.imoviedb.app.domain.account.model

import com.imoviedb.app.domain.base.BaseDomainModel

class AccountDomainModel : BaseDomainModel() {

    var avatarHash: String? = null

    var id: Int = 0

    var iso6391: String? = ""

    var iso31661: String? = ""

    var name: String? = ""

    var includeAdult: Boolean = false

    var username: String? = ""
}

