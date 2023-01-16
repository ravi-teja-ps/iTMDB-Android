package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenModelEntityMapper
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class GuestAutTokenDtoDomainMapperTest {

    private lateinit var guestAutTokenModelEntityMapper: GuestAutTokenModelEntityMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        guestAutTokenModelEntityMapper= GuestAutTokenModelEntityMapper()
    }


    @Test
    fun `test convertModelToEntity`() {
        val mockInput = mock(GuestAuthCreateTokenDomainModel::class.java)
        val returnObject= guestAutTokenModelEntityMapper.map(mockInput)
        assertNotNull(returnObject)
        assertNotNull(returnObject.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)

    }
}