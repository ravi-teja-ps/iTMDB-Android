package com.imoviedb.app.data.dto.authentication.mapper.guesttoken

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.GuestUserTokenEntity
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import javax.inject.Inject

class GuestAutTokenModelEntityMapper @Inject constructor() : Mapper<GuestAuthCreateTokenDomainModel,GuestUserTokenEntity> {


    override fun map(model: GuestAuthCreateTokenDomainModel): GuestUserTokenEntity {
        return GuestUserTokenEntity().apply {
            requestToken = model.request_token ?: ""
            expiresAt = model.expiresAt
            status_code = model.statusCode
            success = model.success
            status_message = model.statusMessage
        }
    }
}