package com.imoviedb.app.data.networking.utils

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptorImpl @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
            //.addHeader("Authorization","Bearer ${NetworkUtils.API_ACCESS_TOKEN}")
            .addHeader("Content-Type" ,"application/json")
            .build())
    }
}