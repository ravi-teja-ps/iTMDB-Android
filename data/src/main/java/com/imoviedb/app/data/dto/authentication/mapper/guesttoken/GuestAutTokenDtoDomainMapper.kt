package com.imoviedb.app.data.dto.authentication.mapper.guesttoken

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import javax.inject.Inject

class GuestAutTokenDtoDomainMapper @Inject constructor() :
    Mapper<GuestAuthCreateTokenDto, GuestAuthCreateTokenDomainModel> {

    override fun map(input: GuestAuthCreateTokenDto): GuestAuthCreateTokenDomainModel {
        return GuestAuthCreateTokenDomainModel().apply {
            requestToken = input.requestToken
            expiresAt = input.expiresAt
            statusCode = input.statusCode
            success = input.success
            statusMessage = input.statusMessage
        }
    }
}