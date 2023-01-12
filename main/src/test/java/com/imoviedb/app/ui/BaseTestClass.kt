package com.imoviedb.app.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imoviedb.app.data.concurrencyutils.TestCoroutineRule
import com.imoviedb.app.data.concurrencyutils.TestDispatcherProvider
import com.imoviedb.app.data.networking.utils.DispatcherProvider
import org.junit.Before

import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

abstract class BaseTestClass {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var dispatcherProvider: DispatcherProvider

    @Before
    abstract fun setup()

     fun initTestWithPrerequisites(){
        dispatcherProvider = TestDispatcherProvider()
        MockitoAnnotations.initMocks(this)
    }

}