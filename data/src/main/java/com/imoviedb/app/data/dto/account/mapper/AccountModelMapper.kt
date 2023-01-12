package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.data.storage.account.AccountEntity
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject


class AccountModelMapper @Inject constructor() {

    fun mapDtoToDomainModel(accountModel: AccountDto) : AccountDomainModel {
        return AccountDomainModel().apply {
            id = accountModel.id ?: -1
            name = accountModel.name
            includeAdult = accountModel.includeAdult
            iso31661 = accountModel.iso31661
            iso6391 = accountModel.iso6391
            username = accountModel.username
            statusCode = accountModel.statusCode?.toInt() ?: 0
            statusMessage = accountModel.statusMessage
            avatarHash = accountModel.avatarDto?.gravatarDto?.hash
        }
    }


    fun mapModelToEntity(accountModel: AccountDomainModel) : AccountEntity {
        return AccountEntity().apply {
            id = accountModel.id ?: -1
            name = accountModel.name
            includeAdult = accountModel.includeAdult
            iso31661 = accountModel.iso31661
            iso6391 = accountModel.iso6391
            username = accountModel.username
            statusCode = accountModel.statusCode
            statusMessage = accountModel.statusMessage
            avatarHash = accountModel.avatarHash
        }
    }
}