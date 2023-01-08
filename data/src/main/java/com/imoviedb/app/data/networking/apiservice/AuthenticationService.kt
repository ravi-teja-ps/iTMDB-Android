package com.imoviedb.app.data.networking.apiservice

import com.imoviedb.app.data.models.authentication.AccessTokenValidateModel
import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import com.imoviedb.app.data.models.authentication.NewSessionModel

import com.imoviedb.app.data.networking.utils.ApiServiceUtils
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * API service endpoint for Authentication , new token and new session for login
 */
interface AuthenticationService {

    @POST("authentication/token/validate_with_login")
    suspend fun authenticateUserDetails(
        @Query("api_key") api_key: String = ApiServiceUtils.API_KEY_V3,
        @Body requestBody: HashMap<String, String>
    ): Response<AccessTokenValidateModel>


    @GET("authentication/token/new")
    suspend fun createApiToken(@Query("api_key") api_key: String = ApiServiceUtils.API_KEY_V3):
            Response<GuestAuthCreateTokenModel>


    @POST("authentication/session/new")
    suspend fun createSessionID(
        @Query("api_key") api_key: String = ApiServiceUtils.API_KEY_V3,
        @Body requestBody: HashMap<String, String>
    ): Response<NewSessionModel>
}