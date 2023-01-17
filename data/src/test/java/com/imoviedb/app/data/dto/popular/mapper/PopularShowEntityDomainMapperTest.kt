package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PopularShowEntityDomainMapperTest {

    private lateinit var popularShowEntityModelMapper: PopularShowEntityModelMapper


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        popularShowEntityModelMapper= PopularShowEntityModelMapper()
    }

    @Test
    fun `test conversion of entity to model`() {
        val mockInput = Mockito.mock(ShowEntityModel::class.java)
        val returnObject =popularShowEntityModelMapper.map(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }
}