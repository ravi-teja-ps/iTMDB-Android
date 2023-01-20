package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject


class AccountDtoModelMapper @Inject constructor() : Mapper<AccountDto, AccountDomainModel> {

    override fun map(input: AccountDto): AccountDomainModel {
        return AccountDomainModel().apply {
            id = input.id
            name = input.name
            includeAdult = input.includeAdult
            iso31661 = input.iso31661
            iso6391 = input.iso6391
            username = input.username
            statusCode = input.statusCode
            statusMessage = input.statusMessage
            avatarHash = input.avatarDto?.gravatarDto?.hash
        }
    }
}