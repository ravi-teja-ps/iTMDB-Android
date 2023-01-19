package com.imoviedb.app.data.mappers

import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 * Test class to implement base mocking importing
 */
abstract class BaseMapperTest {

    /** setup your class under Test initializations  in this */
    abstract fun postSetup()

    //Test to first confirm if the class under test is an instance of Mapper
    abstract fun testInstanceOfSubject()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postSetup()
    }

    @Test
    fun `test if given class is instance of Mapper interface`(){
        testInstanceOfSubject()
    }
}