package com.imoviedb.app.data.dto.authentication.mapper.newsession

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionModelEntityMapper @Inject constructor() :
    Mapper<NewSessionDomainModel, UserSessionEntity> {

    override fun map(input: NewSessionDomainModel): UserSessionEntity {
        return UserSessionEntity(
            sessionId = input.sessionId,
            success = input.success,
            statusCode = input.statusCode,
            statusMessage = input.statusMessage,
            expiresAt = input.expiresAt
        )
    }
}