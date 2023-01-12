package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class GuestAutTokenMapperTest {

    private lateinit var guestAutTokenMapper: GuestAutTokenMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        guestAutTokenMapper= GuestAutTokenMapper()
    }

    @Test
    fun convertDtoToModel() {
        val mockInput = mock(GuestAuthCreateTokenDto::class.java)
        val returnObject= guestAutTokenMapper.convertDtoToModel(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.request_token, mockInput.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    @Test
    fun convertModelToEntity() {
        val mockInput = mock(GuestAuthCreateTokenDomainModel::class.java)
        val returnObject= guestAutTokenMapper.convertModelToEntity(mockInput)
        assertNotNull(returnObject)
        assertNotNull(returnObject.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)

    }
}