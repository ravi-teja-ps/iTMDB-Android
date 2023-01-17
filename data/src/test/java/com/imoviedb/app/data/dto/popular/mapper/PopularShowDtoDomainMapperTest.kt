package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.popular.ShowDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PopularShowDtoDomainMapperTest {

    private lateinit var popularShowDtoDomainMapper: PopularShowDtoDomainMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        popularShowDtoDomainMapper= PopularShowDtoDomainMapper()
    }

    @Test
    fun `test conversion of dto to domain model`() {
        val mockInput = Mockito.mock(ShowDto::class.java)
        val returnObject =popularShowDtoDomainMapper.map(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }

}