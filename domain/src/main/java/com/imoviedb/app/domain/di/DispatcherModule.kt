package com.imoviedb.app.domain.di

import com.imoviedb.app.domain.concurrency.DefaultDispatcherProvider
import com.imoviedb.app.domain.concurrency.DispatcherProvider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A Hilt DI injector for providing dispatcher for coroutines Dispatcher across the app
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class DispatcherModule {

    @Binds
    abstract fun defaultDispatcher(defaultDispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}