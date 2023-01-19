package com.imoviedb.app.data.dto.authentication.mapper.newsession

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import javax.inject.Inject

class NewSessionErrorDtoModelMapper @Inject constructor() :
    Mapper<ErrorResponseDto, NewSessionDomainModel> {

    override fun map(from: ErrorResponseDto): NewSessionDomainModel {
        return NewSessionDomainModel().apply {
            success = from.success
            statusCode = from.statusCode
            statusMessage = from.statusMessage
        }
    }
}