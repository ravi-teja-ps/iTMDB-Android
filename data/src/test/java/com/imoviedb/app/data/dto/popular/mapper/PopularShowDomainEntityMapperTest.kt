package com.imoviedb.app.data.dto.popular.mapper

import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
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
    fun convertDtoToDomainModel() {
        val mockInput = Mockito.mock(ShowDto::class.java)
        val returnObject =popularShowDomainEntityMapper.convertDtoToDomainModel(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }

    @Test
    fun convertModelToDbEntity() {
        val mockInput = Mockito.mock(ShowDomainModel::class.java)
        val returnObject =popularShowDomainEntityMapper.convertModelToDbEntity(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }

    @Test
    fun convertEntityToModel() {
        val mockInput = Mockito.mock(ShowEntityModel::class.java)
        val returnObject =popularShowDomainEntityMapper.convertEntityToModel(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }
}