package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.NewSessionDto
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionMapper @Inject constructor(){

    fun dtoToModel(newSessionDto: NewSessionDto): NewSessionDomainModel{
        return  NewSessionDomainModel().apply {
            sessionId = newSessionDto.sessionId
            success = newSessionDto.success
            statusCode = newSessionDto.statusCode?.toInt() ?: -1
            statusMessage = newSessionDto.statusMessage
        }
    }

    fun modelToEntity(domainModel: NewSessionDomainModel): UserSessionEntity{
        return  UserSessionEntity().apply {
            session_id = domainModel.sessionId ?: ""
            success = domainModel.success
            status_code = domainModel.statusCode.toString()
            status_message = domainModel.statusMessage
        }
    }
}