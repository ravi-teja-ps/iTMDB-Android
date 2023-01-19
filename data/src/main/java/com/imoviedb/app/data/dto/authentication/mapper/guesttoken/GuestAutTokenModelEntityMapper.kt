package com.imoviedb.app.data.dto.authentication.mapper.guesttoken

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.GuestUserTokenEntity
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import javax.inject.Inject

class GuestAutTokenModelEntityMapper @Inject constructor() :
    Mapper<GuestAuthCreateTokenDomainModel, GuestUserTokenEntity> {


    override fun map(input: GuestAuthCreateTokenDomainModel): GuestUserTokenEntity {
        return GuestUserTokenEntity().apply {
            requestToken = input.requestToken ?: ""
            expiresAt = input.expiresAt
            statusCode = input.statusCode
            success = input.success
            statusMessage = input.statusMessage
        }
    }
}