package com.imoviedb.app.data.dto.authentication.mapper.accesstoken

import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import javax.inject.Inject

class AccessTokenValidateDtoModelMapper @Inject constructor() :
    Mapper<AccessTokenValidateDto, AccessTokenValidateDomainModel> {
    override fun map(input: AccessTokenValidateDto): AccessTokenValidateDomainModel {
        return AccessTokenValidateDomainModel(
            success = input.success,
            requestToken = input.requestToken,
            expiresAt = input.expiresAt,
            statusCode = input.statusCode,
            statusMessage = input.statusMessage
        )
    }
}
