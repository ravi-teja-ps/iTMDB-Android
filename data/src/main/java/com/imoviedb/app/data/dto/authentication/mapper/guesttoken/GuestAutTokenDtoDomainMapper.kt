package com.imoviedb.app.data.dto.authentication.mapper.guesttoken

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import javax.inject.Inject

class GuestAutTokenDtoDomainMapper @Inject constructor() : Mapper<GuestAuthCreateTokenDto,GuestAuthCreateTokenDomainModel> {

    override fun map(from : GuestAuthCreateTokenDto): GuestAuthCreateTokenDomainModel {
        return GuestAuthCreateTokenDomainModel().apply {
            request_token = from.requestToken
            expiresAt = from.expiresAt
            statusCode = from.statusCode
            success = from.success
            statusMessage = from.statusMessage
        }
    }
}