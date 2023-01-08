package com.imoviedb.app.data.di

import com.imoviedb.app.data.BuildConfig
import com.imoviedb.app.data.networking.utils.ApiServiceUtils
import com.imoviedb.app.data.networking.utils.NetworkInterceptorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * A Hilt module for Network Retrofit, OKHTTP, Interceptor and logging
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient) : Retrofit {

        return Retrofit.Builder()
            .baseUrl(ApiServiceUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun okhttpClient(networkInterceptor : NetworkInterceptorImpl, httpLogger : HttpLoggingInterceptor) : OkHttpClient {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(networkInterceptor)
        if(BuildConfig.DEBUG){
            okHttpClient.addInterceptor(httpLogger)
        }
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun okhttpLogging() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }
}