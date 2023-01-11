package com.imoviedb.app.data.di

import android.content.Context
import androidx.room.Room
import com.imoviedb.app.data.storage.AppDatabase
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.GuestUserTokenDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserTokenDAO
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.storage.popularshows.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A database Hilt module injected across application for providing DAO db implementation
 */
@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun roomDbInstance(@ApplicationContext appContext : Context) : AppDatabase{
         return  Room.databaseBuilder(  appContext , AppDatabase::class.java, "appDataBase").build()
    }

    @Singleton
    @Provides
    fun guestUserTokenDAO(appDataBase: AppDatabase) : GuestUserTokenDAO = appDataBase.guestUserTokenDao()

    @Singleton
    @Provides
    fun userTokenDAO(appDataBase: AppDatabase) : UserTokenDAO = appDataBase.userTokenDao()

    @Provides
    fun accountDAO(appDataBase: AppDatabase) :AccountDAO = appDataBase.accountDao()

    @Provides
    fun userSessionDAO(appDataBase: AppDatabase) :UserSessionDAO = appDataBase.userSessionDao()


    @Provides
    fun popularShowDao(appDataBase: AppDatabase) :PopularShowsDao = appDataBase.popularShowsDao()

    @Provides
    fun remoteKeyDao(appDataBase: AppDatabase) :RemoteKeyDao = appDataBase.remoteKeyDao()
}