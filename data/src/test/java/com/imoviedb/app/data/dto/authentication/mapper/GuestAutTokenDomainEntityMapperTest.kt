package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenDtoDomainMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class GuestAutTokenDomainEntityMapperTest {

    private lateinit var guestAutTokenDtoDomainMapper: GuestAutTokenDtoDomainMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        guestAutTokenDtoDomainMapper= GuestAutTokenDtoDomainMapper()
    }

    @Test
    fun `test convertDtoToModel`() {
        val mockInput = mock(GuestAuthCreateTokenDto::class.java)
        val returnObject= guestAutTokenDtoDomainMapper.map(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.request_token, mockInput.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

}