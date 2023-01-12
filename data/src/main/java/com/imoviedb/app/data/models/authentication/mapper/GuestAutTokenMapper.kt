package com.imoviedb.app.data.models.authentication.mapper

import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import com.imoviedb.app.data.storage.authentication.GuestUserTokenEntity
import javax.inject.Inject

class GuestAutTokenMapper @Inject constructor(){
    fun convertModelToEntity(input: GuestAuthCreateTokenModel): GuestUserTokenEntity {
        return GuestUserTokenEntity().apply {
            request_token = input.request_token ?: ""
            expiresAt = input.expiresAt
            status_code = input.status_code
            success = input.success
            status_message = input.status_message
        }
    }

    fun convertEntityToModel(input : GuestUserTokenEntity): GuestAuthCreateTokenModel {
        return GuestAuthCreateTokenModel().apply {
            request_token = input.request_token
            expiresAt = input.expiresAt
            status_code = input.status_code
            success = input.success
            status_message = input.status_message
        }
    }
}