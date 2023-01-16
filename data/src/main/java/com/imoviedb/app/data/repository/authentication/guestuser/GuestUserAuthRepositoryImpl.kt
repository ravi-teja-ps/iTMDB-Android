package com.imoviedb.app.data.repository.authentication.guestuser

import com.imoviedb.app.data.dto.ErrorResponseDto
import com.imoviedb.app.data.dto.authentication.mapper.GuestAutTokenMapper
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.utils.toErrorModel
import com.imoviedb.app.data.storage.authentication.GuestUserTokenDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GuestUserAuthRepositoryImpl @Inject constructor(private val authenticationService: AuthenticationService,
                                                      private val guestUserTokenDAO: GuestUserTokenDAO,
                                                      private var guestAutTokenMapper: GuestAutTokenMapper

) : com.imoviedb.app.domain.authentication.guestuser.repository.GuestUserAuthRepository {


    override suspend fun createGuestTokenForSession(coroutineDispatcher: CoroutineDispatcher) = flow {

        val response = authenticationService.createApiToken()
        if(response.isSuccessful) {
           response.body()?.let {
                //Store data token in local storage
                val domainModel = guestAutTokenMapper.convertDtoToModel(it)
                guestUserTokenDAO.saveToken(guestAutTokenMapper.convertModelToEntity(domainModel))
                //send data to UI and view model
                emit(domainModel)
            }
        }
        else{ //error case handling, can enhance this to  emit<errorModel extends BaseModel>
           val errorModel = response.errorBody()!!.toErrorModel<ErrorResponseDto>()
           emit(guestAutTokenMapper.convertErrorDtoToModel(errorModel))
        }
    }.flowOn(coroutineDispatcher)


    override suspend fun deletePreviousGuestToken(coroutineDispatcher: CoroutineDispatcher) = flow<Unit> {
       guestUserTokenDAO.removeToken()
    }.flowOn(coroutineDispatcher)
}