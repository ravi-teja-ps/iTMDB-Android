package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.account.AccountEntity
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject

class AccountModelEntityMapper @Inject constructor() : Mapper<AccountDomainModel, AccountEntity> {

    override fun map(from: AccountDomainModel): AccountEntity {
        return AccountEntity().apply {
            id = from.id ?: -1
            name = from.name
            includeAdult = from.includeAdult
            iso31661 = from.iso31661
            iso6391 = from.iso6391
            username = from.username
            statusCode = from.statusCode
            statusMessage = from.statusMessage
            avatarHash = from.avatarHash
        }
    }
}