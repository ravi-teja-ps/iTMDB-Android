package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.ErrorResponseDto
import com.imoviedb.app.data.dto.authentication.NewSessionDto
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionMapper @Inject constructor(){

    fun dtoToModel(newSessionDto: NewSessionDto): NewSessionDomainModel{
        return  NewSessionDomainModel().apply {
            sessionId = newSessionDto.sessionId
            success = newSessionDto.success
            statusCode = newSessionDto.statusCode
            statusMessage = newSessionDto.statusMessage
        }
    }

    fun modelToEntity(domainModel: NewSessionDomainModel): UserSessionEntity{
        return  UserSessionEntity().apply {
            session_id = domainModel.sessionId ?: ""
            success = domainModel.success
            status_code = domainModel.statusCode
            status_message = domainModel.statusMessage
        }
    }

    fun errorDtoToModel(errorDto: ErrorResponseDto): NewSessionDomainModel{
        return  NewSessionDomainModel().apply {
            success = errorDto.success
            statusCode = errorDto.statusCode
            statusMessage = errorDto.statusMessage
        }
    }
}