package com.imoviedb.app.data.repository.authentication.guestuser

import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenErrorDtoDomainMapper
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenModelEntityMapper
import com.imoviedb.app.data.dto.base.ErrorResponseDto
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.toErrorModel
import com.imoviedb.app.data.storage.authentication.GuestUserTokenDAO
import com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GuestUserAuthRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val guestUserTokenDAO: GuestUserTokenDAO,
    private val guestAutTokenDtoDomainMapper: GuestAutTokenDtoDomainMapper,
    private val guestAutTokenModelEntityMapper: GuestAutTokenModelEntityMapper,
    private val guestAuthTokenValidateErrorModelMapper: GuestAutTokenErrorDtoDomainMapper
) : GuestUserAuthRepository {


    override suspend fun createGuestTokenForSession(coroutineDispatcher: CoroutineDispatcher) =
        flow {
            val response = authenticationService.createApiToken()
            if (response.isSuccessful && response.body()!=null) {
                response.body()?.let {
                    //Store data token in local storage
                    val domainModel = guestAutTokenDtoDomainMapper.map(it)
                    guestUserTokenDAO.saveToken(guestAutTokenModelEntityMapper.map(domainModel))
                    //send data to UI and view model
                    emit(domainModel)
                }
            } else { //error case handling, can enhance this to  emit<errorModel extends BaseModel>
                val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
                emit(guestAuthTokenValidateErrorModelMapper.map(errorModel))
            }
        }.flowOn(coroutineDispatcher)


    override suspend fun deletePreviousGuestToken(coroutineDispatcher: CoroutineDispatcher) =
        flow<Unit> {
            guestUserTokenDAO.removeToken()
        }.flowOn(coroutineDispatcher)
}