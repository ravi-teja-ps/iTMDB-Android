package com.imoviedb.app.data.dto.authentication.mapper.guesttoken

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import javax.inject.Inject

class GuestAutTokenErrorDtoDomainMapper @Inject constructor() : Mapper<ErrorResponseDto,GuestAuthCreateTokenDomainModel> {

    override fun map(input: ErrorResponseDto): GuestAuthCreateTokenDomainModel {
        return GuestAuthCreateTokenDomainModel().apply {
            statusCode = input.statusCode
            success =   input.success
            statusMessage = input.statusMessage
        }
    }
}