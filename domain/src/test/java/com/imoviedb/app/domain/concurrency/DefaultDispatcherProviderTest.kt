package com.imoviedb.app.domain.concurrency

import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Test

class DefaultDispatcherProviderTest {

    private val testDispatcherProvider = DefaultDispatcherProvider()

    @Test
    fun getMain() {
        Assert.assertEquals(testDispatcherProvider.main, Dispatchers.Main)
    }

    @Test
    fun getIo() {
        Assert.assertEquals(testDispatcherProvider.io, Dispatchers.IO)
    }

    @Test
    fun getDefault() {
        Assert.assertEquals(testDispatcherProvider.default, Dispatchers.Default)
    }
}