package com.imoviedb.app.data.dto.authentication.mapper.newsession

import com.imoviedb.app.data.dto.authentication.NewSessionDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionDtoDomainMapper @Inject constructor() :
    Mapper<NewSessionDto, NewSessionDomainModel> {

    override fun map(input: NewSessionDto): NewSessionDomainModel {
        return NewSessionDomainModel(
            sessionId = input.sessionId,
            success = input.success,
            statusCode = input.statusCode,
            statusMessage = input.statusMessage,
            expiresAt = input.expiresAt
        )
    }
}