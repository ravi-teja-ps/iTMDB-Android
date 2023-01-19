package com.imoviedb.app.data.dto.authentication.mapper.accesstoken

import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import javax.inject.Inject

class AccessTokenValidateDtoModelMapper @Inject constructor() :
    Mapper<AccessTokenValidateDto, AccessTokenValidateDomainModel> {
    override fun map(from: AccessTokenValidateDto): AccessTokenValidateDomainModel {
        return AccessTokenValidateDomainModel().apply {
            success = from.success
            requestToken = from.requestToken
            expiresAt = from.expiresAt
        }
    }
}
