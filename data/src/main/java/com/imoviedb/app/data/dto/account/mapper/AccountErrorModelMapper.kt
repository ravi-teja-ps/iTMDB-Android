package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.domain.account.model.AccountDomainModel
import javax.inject.Inject

class AccountErrorModelMapper @Inject constructor() : Mapper<ErrorResponseDto, AccountDomainModel> {
    override fun map(input: ErrorResponseDto): AccountDomainModel {
        return AccountDomainModel(
            statusCode = input.statusCode,
            statusMessage = input.statusMessage,
            //Defaults to null or -1 or false for unsued values
            avatarHash = null,
            id = -1,
            iso6391 = null,
            iso31661 = null,
            name = null,
            includeAdult = false,
            username = null
        )
    }
}