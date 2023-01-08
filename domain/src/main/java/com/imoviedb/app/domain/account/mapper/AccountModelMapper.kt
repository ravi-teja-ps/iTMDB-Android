package com.imoviedb.app.domain.account.mapper

import com.imoviedb.app.data.models.account.AccountModel
import com.imoviedb.app.data.models.account.Avatar
import com.imoviedb.app.data.models.account.Gravatar
import com.imoviedb.app.data.storage.account.AccountEntity
import javax.inject.Inject


class AccountModelMapper @Inject constructor() {
    fun mapEntityToModel(accountEntity: AccountEntity) : AccountModel {
        return  AccountModel().apply {
            id = accountEntity.id
            name =accountEntity.name
            includeAdult=accountEntity.includeAdult
            iso31661=accountEntity.iso31661
            iso6391=accountEntity.iso6391
            username=accountEntity.username
            status_code= (accountEntity.statusCode).toString()
            status_message=accountEntity.statusMessage
            val gravatarObj = Gravatar().apply {
                hash= accountEntity.avatarHash
            }
            avatar = Avatar().apply {
                gravatar = gravatarObj
            }
        }
    }

    fun mapModelToEntity(accountModel: AccountModel) : AccountEntity {
        return AccountEntity().apply {
            id = accountModel.id ?: -1
            name = accountModel.name
            includeAdult = accountModel.includeAdult
            iso31661 = accountModel.iso31661
            iso6391 = accountModel.iso6391
            username = accountModel.username
            statusCode = accountModel.status_code?.toInt() ?: 0
            statusMessage = accountModel.status_message
            avatarHash = accountModel.avatar?.gravatar?.hash
        }
    }
}