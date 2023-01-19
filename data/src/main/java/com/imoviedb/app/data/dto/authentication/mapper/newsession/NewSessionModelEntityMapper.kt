package com.imoviedb.app.data.dto.authentication.mapper.newsession

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionModelEntityMapper @Inject constructor() :
    Mapper<NewSessionDomainModel, UserSessionEntity> {

    override fun map(from: NewSessionDomainModel): UserSessionEntity {
        return UserSessionEntity().apply {
            sessionId = from.sessionId ?: ""
            success = from.success
            statusCode = from.statusCode
            statusMessage = from.statusMessage
        }
    }
}