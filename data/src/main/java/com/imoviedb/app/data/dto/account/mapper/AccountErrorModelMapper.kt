package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject

class AccountErrorModelMapper @Inject constructor(): Mapper<ErrorResponseDto, AccountDomainModel> {
    override fun map(from: ErrorResponseDto) : AccountDomainModel {
        return AccountDomainModel().apply {
            statusCode = from.statusCode
            statusMessage = from.statusMessage
        }
    }
}