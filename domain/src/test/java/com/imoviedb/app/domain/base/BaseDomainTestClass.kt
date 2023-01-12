package com.imoviedb.app.domain.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imoviedb.app.data.concurrencyutils.TestCoroutineRule
import com.imoviedb.app.data.concurrencyutils.TestDispatcherProvider
import com.imoviedb.app.data.networking.utils.DispatcherProvider
import org.junit.Before

import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

abstract class BaseDomainTestClass {

    abstract fun onPostSetup()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup() {
        initalizeCommonDependencies()
        onPostSetup()
    }

    private fun initalizeCommonDependencies(){
        dispatcherProvider = TestDispatcherProvider()
        MockitoAnnotations.initMocks(this)
    }

}