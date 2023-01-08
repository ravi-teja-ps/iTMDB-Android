package com.imoviedb.app.data.di

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
   abstract fun defaultDispatcher(defaultDispatcherProvider: DefaultDispatcherProvider) : DispatcherProvider
}