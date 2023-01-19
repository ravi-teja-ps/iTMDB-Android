package com.imoviedb.app.data.dto.authentication.mapper.accesstoken

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import javax.inject.Inject

class AccessTokenValidateErrorModelMapper @Inject constructor() :
    Mapper<ErrorResponseDto, AccessTokenValidateDomainModel> {

    //mapErrorDtoToModel
    override fun map(from: ErrorResponseDto): AccessTokenValidateDomainModel {
        return AccessTokenValidateDomainModel().apply {
            success = from.success
            statusMessage = from.statusMessage
            statusCode = from.statusCode
        }
    }
}