package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateModelEntityMapper
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AccessTokenValidateModelEntityMapperTest {

    private  lateinit var accessTokenValidateModelEntityMapper: AccessTokenValidateModelEntityMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accessTokenValidateModelEntityMapper= AccessTokenValidateModelEntityMapper()
    }

    @Test
    fun `test mapModelToEntity`() {
        val mockInput = mock(AccessTokenValidateDomainModel::class.java)
        val returnObject =accessTokenValidateModelEntityMapper.map(mockInput)
        assertNotNull(returnObject)
        assertNotNull(returnObject.request_token)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }
}