package com.imoviedb.app.data.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imoviedb.app.data.concurrencyutils.TestCoroutineRule
import com.imoviedb.app.data.concurrencyutils.TestDispatcherProvider
import com.imoviedb.app.domain.concurrency.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

//A class to reduce boilerplate code in Data test class modules
@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseDataTestClass {

    abstract fun onPostSetup()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup() {
        //Pre initialize
        dispatcherProvider = TestDispatcherProvider()
        MockitoAnnotations.initMocks(this)
        //invoke child @Before overriding method
        onPostSetup()
    }

}