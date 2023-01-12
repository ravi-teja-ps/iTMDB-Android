package com.imoviedb.app.domain.account.model

import com.imoviedb.app.domain.base.BaseDomainModel

class AccountDomainModel : BaseDomainModel() {

    var avatarHash: String? = null

    var id: Int? = null

    var iso6391: String? = null

    var iso31661: String? = null

    var name: String? = null

    var includeAdult: Boolean? = null

    var username: String? = null

}

