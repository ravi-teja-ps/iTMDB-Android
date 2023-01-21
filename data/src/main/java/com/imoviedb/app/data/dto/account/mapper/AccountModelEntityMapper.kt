package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.account.AccountEntity
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject

class AccountModelEntityMapper @Inject constructor() : Mapper<AccountDomainModel, AccountEntity> {

    override fun map(input: AccountDomainModel): AccountEntity {
        return AccountEntity(
            id = input.id,
            name = input.name,
            includeAdult = input.includeAdult,
            iso31661 = input.iso31661,
            iso6391 = input.iso6391,
            username = input.username,
            statusCode = input.statusCode,
            statusMessage = input.statusMessage,
            avatarHash = input.avatarHash
        )
    }
}