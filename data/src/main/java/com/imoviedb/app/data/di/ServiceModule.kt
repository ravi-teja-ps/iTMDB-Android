package com.imoviedb.app.data.di

import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.networking.apiservice.AuthenticationService
import com.imoviedb.app.data.networking.apiservice.PopularShowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * A hilt DI module for injecting a service modules for API services
 */
@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    fun getAccountService(retrofit: Retrofit): AccountService {
        return retrofit.create(AccountService::class.java)
    }

    @Provides
    fun getAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    fun getPopularService(retrofit: Retrofit): PopularShowService {
        return retrofit.create(PopularShowService::class.java)
    }
}