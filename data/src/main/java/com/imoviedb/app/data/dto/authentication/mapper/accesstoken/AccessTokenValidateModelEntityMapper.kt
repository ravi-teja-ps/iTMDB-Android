package com.imoviedb.app.data.dto.authentication.mapper.accesstoken

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.UserTokenEntity
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import javax.inject.Inject

class AccessTokenValidateModelEntityMapper @Inject constructor() :
    Mapper<AccessTokenValidateDomainModel, UserTokenEntity> {

    //mapModelToEntity
    override fun map(input: AccessTokenValidateDomainModel): UserTokenEntity {
        return UserTokenEntity().apply {
            success = input.success
            requestToken = input.requestToken ?: ""
            expiresAt = input.expiresAt
        }
    }
}