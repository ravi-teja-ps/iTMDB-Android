package com.imoviedb.app.domain.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseDomainTestClass {

    abstract fun postSetup()

    @Before
    fun initTestWithPrerequisites() {
         MockitoAnnotations.initMocks(this)
        postSetup()
    }
}