package com.imoviedb.app.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.account.AccountEntity
import com.imoviedb.app.data.storage.authentication.*
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import com.imoviedb.app.data.storage.popularshows.RemoteKey
import com.imoviedb.app.data.storage.popularshows.RemoteKeyDao

@Database(entities =
[GuestUserTokenEntity::class,
   UserTokenEntity::class,
   AccountEntity::class,
   ShowEntityModel::class,
   UserSessionEntity::class,
   RemoteKey::class
], version = 1)
abstract  class AppDatabase  : RoomDatabase(){

   abstract fun guestUserTokenDao(): GuestUserTokenDAO
   abstract fun userTokenDao() : UserTokenDAO
   abstract fun userSessionDao() : UserSessionDAO
   abstract fun accountDao() : AccountDAO
   abstract fun popularShowsDao() : PopularShowsDao
   abstract fun remoteKeyDao() : RemoteKeyDao
}