package com.imoviedb.app.data.dto.authentication.mapper.accesstoken

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.UserTokenEntity
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import javax.inject.Inject

class AccessTokenValidateModelEntityMapper @Inject constructor() :
    Mapper<AccessTokenValidateDomainModel, UserTokenEntity> {

    //mapModelToEntity
    override fun map(from: AccessTokenValidateDomainModel): UserTokenEntity {
        return UserTokenEntity().apply {
            success = from.success
            requestToken = from.requestToken ?: ""
            expiresAt = from.expiresAt
        }
    }
}