package com.imoviedb.app.data.repository.authentication.guestuser

import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.asErrorModel
import com.imoviedb.app.data.networking.utils.isSuccess
import com.imoviedb.app.data.storage.authentication.GuestUserTokenDAO
import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GuestUserAuthRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val guestUserTokenDAO: GuestUserTokenDAO,
    private val guestAutTokenDtoDomainMapper: GuestAutTokenDtoDomainMapper,
    private val guestAutTokenModelEntityMapper: GuestAutTokenModelEntityMapper,
    private val dispatcherProvider: DispatcherProvider
) : GuestUserAuthRepository {


    override suspend fun createGuestTokenForSession() =
        flow {
            val response = authenticationService.createApiToken()
            if (response.isSuccess()) {
                response.body()?.let {
                    //Store data token in local storage
                    with(guestAutTokenDtoDomainMapper.map(it)){
                        guestUserTokenDAO.saveToken(guestAutTokenModelEntityMapper.map(this))
                        emit(ResponseWrapper.Success(this))
                    }
                }
            } else {
                with(response.asErrorModel()){
                    emit(ResponseWrapper.Error(statusCode,statusMessage))
                }
            }
        }.flowOn(dispatcherProvider.io)


    override suspend fun deletePreviousGuestToken() =
        flow<Unit> {
            guestUserTokenDAO.removeToken()
        }.flowOn(dispatcherProvider.io)
}