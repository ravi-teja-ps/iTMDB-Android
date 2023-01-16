package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.ErrorResponseDto
import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.data.storage.authentication.UserTokenEntity
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import javax.inject.Inject

class AccessTokenValidateMapper @Inject constructor() {
    fun mapDtoToModel(accessTokenValidateDto: AccessTokenValidateDto): AccessTokenValidateDomainModel {
        return AccessTokenValidateDomainModel().apply {
            success = accessTokenValidateDto.success
            requestToken =accessTokenValidateDto.requestToken
            expiresAt = accessTokenValidateDto.expiresAt
        }
    }

    fun mapModelToEntity(accessTokenValidateModel: AccessTokenValidateDomainModel): UserTokenEntity {
        return UserTokenEntity().apply {
            success = accessTokenValidateModel.success
            request_token =accessTokenValidateModel.requestToken?:""
            expiresAt = accessTokenValidateModel.expiresAt
        }
    }

    fun mapErrorDtoToModel(errorDto: ErrorResponseDto): AccessTokenValidateDomainModel {
        return AccessTokenValidateDomainModel().apply {
            success = errorDto.success
            statusMessage = errorDto.statusMessage
            statusCode  = errorDto.statusCode
        }
    }
}
