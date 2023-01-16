package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateDtoModelMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AccessTokenValidateDtoModelMapperTest {

    private  lateinit var accessTokenValidateDtoModelMapper: AccessTokenValidateDtoModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accessTokenValidateDtoModelMapper= AccessTokenValidateDtoModelMapper()
    }

    @Test
    fun `test mapDtoToModel`() {
        val mockInput = mock(AccessTokenValidateDto::class.java)
        val returnObject =accessTokenValidateDtoModelMapper.map(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.requestToken, mockInput.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }
}