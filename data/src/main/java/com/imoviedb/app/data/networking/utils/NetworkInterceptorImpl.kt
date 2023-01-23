package com.imoviedb.app.data.networking.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor to add header for content type .We can also add access token for non authenticated users
 */
class NetworkInterceptorImpl @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                //.addHeader("Authorization","Bearer ${NetworkUtils.API_ACCESS_TOKEN}")
                .addHeader(CONTENT_TYPE_KEY, MIMETYPE_KEY)
                .build()
        )
    }

    private companion object{
        const val CONTENT_TYPE_KEY = "Content-Type"
        const val MIMETYPE_KEY = "application/json"
    }
}