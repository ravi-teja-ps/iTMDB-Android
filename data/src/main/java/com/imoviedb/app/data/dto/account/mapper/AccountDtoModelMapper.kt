package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject


class AccountDtoModelMapper @Inject constructor() : Mapper<AccountDto, AccountDomainModel> {

    override fun map(accountModel: AccountDto): AccountDomainModel {
        return AccountDomainModel().apply {
            id = accountModel.id ?: -1
            name = accountModel.name
            includeAdult = accountModel.includeAdult
            iso31661 = accountModel.iso31661
            iso6391 = accountModel.iso6391
            username = accountModel.username
            statusCode = accountModel.statusCode
            statusMessage = accountModel.statusMessage
            avatarHash = accountModel.avatarDto?.gravatarDto?.hash
        }
    }
}