package com.imoviedb.app.ui

import TestCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imoviedb.app.ui.concurrencyutils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTestClass {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var dispatcherProvider: TestDispatcherProvider


    abstract fun postSetup()

    @Before
    fun initTestWithPrerequisites(){
        dispatcherProvider = TestDispatcherProvider()
        MockitoAnnotations.initMocks(this)
        postSetup()
    }
}