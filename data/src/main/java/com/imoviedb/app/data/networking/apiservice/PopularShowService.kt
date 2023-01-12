package com.imoviedb.app.data.networking.apiservice

import com.imoviedb.app.data.dto.popular.PopularShowsDto
import com.imoviedb.app.data.networking.utils.ApiServiceUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * fetch popular shows api endpoint retrofit
 */
interface PopularShowService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun popularShows(@Path("media_type") mediaType:String = "all",
                             @Path("time_window") timeWindow:String = "day",
                             @Query("api_key") api_key: String = ApiServiceUtils.API_KEY_V3,
                             @Query("page") page : Int) : Response<PopularShowsDto>

}