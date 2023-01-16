package com.imoviedb.app.data.dto.authentication.mapper.newsession

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionModelEntityMapper @Inject constructor() : Mapper<NewSessionDomainModel,UserSessionEntity>{

    override fun map(domainModel: NewSessionDomainModel): UserSessionEntity{
        return  UserSessionEntity().apply {
            session_id = domainModel.sessionId ?: ""
            success = domainModel.success
            status_code = domainModel.statusCode
            status_message = domainModel.statusMessage
        }
    }
}