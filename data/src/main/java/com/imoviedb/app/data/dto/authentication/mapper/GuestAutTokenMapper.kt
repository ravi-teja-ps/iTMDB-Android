package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.data.storage.authentication.GuestUserTokenEntity
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import javax.inject.Inject

class GuestAutTokenMapper @Inject constructor(){

    fun convertDtoToModel(input : GuestAuthCreateTokenDto): GuestAuthCreateTokenDomainModel {
        return GuestAuthCreateTokenDomainModel().apply {
            request_token = input.requestToken
            expiresAt = input.expiresAt
            statusCode = input.statusCode?.toInt() ?: -1
            success = input.success
            statusMessage = input.statusMessage
        }
    }

    fun convertModelToEntity(model: GuestAuthCreateTokenDomainModel): GuestUserTokenEntity {
        return GuestUserTokenEntity().apply {
            requestToken = model.request_token ?: ""
            expiresAt = model.expiresAt
            status_code = model.statusCode.toString()
            success = model.success
            status_message = model.statusMessage
        }
    }
}