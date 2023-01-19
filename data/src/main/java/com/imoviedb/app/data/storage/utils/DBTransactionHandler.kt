package com.imoviedb.app.data.storage.utils

import androidx.room.withTransaction
import com.imoviedb.app.data.storage.AppDatabase
import javax.inject.Inject

/**
 * Helper Class to execute a block of code under RoomDatabase.withTransaction scope
 * to execute read/write and commit transaction. App database sticks to data module only
 *
 */
class DBTransactionHandler @Inject constructor(private val appDatabase: AppDatabase) {
    suspend fun <T> executeTransaction(codeBlock: suspend () -> T): T {
        return appDatabase.withTransaction(codeBlock)
    }
}