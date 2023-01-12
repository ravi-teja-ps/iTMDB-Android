package com.imoviedb.app.data.networking.apiservice

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.data.networking.utils.ApiServiceUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountService {

    /**
     * Fetch account info taking session id and API key as input
     */

    @GET("account")
    suspend fun account(@Query("api_key") api_key:String = ApiServiceUtils.API_KEY_V3,
                        @Query("session_id") session_id: String) : Response<AccountDto>

}