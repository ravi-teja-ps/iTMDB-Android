package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PopularShowDomainEntityMapperTest {

    private lateinit var popularShowDomainEntityMapper: PopularShowDomainEntityMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        popularShowDomainEntityMapper= PopularShowDomainEntityMapper()
    }

    @Test
    fun `test conversion of model to Db entity`() {
        val mockInput = Mockito.mock(ShowDomainModel::class.java)
        val returnObject =popularShowDomainEntityMapper.map(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }

}