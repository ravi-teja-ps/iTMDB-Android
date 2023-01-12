package com.imoviedb.app.data.dto.authentication.mapper

import com.imoviedb.app.data.dto.account.mapper.AccountModelMapper
import com.imoviedb.app.data.dto.authentication.AccessTokenValidateDto
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AccessTokenValidateMapperTest {

    private  lateinit var accessTokenValidateMapper: AccessTokenValidateMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accessTokenValidateMapper= AccessTokenValidateMapper()
    }

    @Test
    fun mapDtoToModel() {
        val mockInput = mock(AccessTokenValidateDto::class.java)
        val returnObject =accessTokenValidateMapper.mapDtoToModel(mockInput)
        assertNotNull(returnObject)
        assertEquals(returnObject.requestToken, mockInput.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    @Test
    fun mapModelToEntity() {
        val mockInput = mock(AccessTokenValidateDomainModel::class.java)
        val returnObject =accessTokenValidateMapper.mapModelToEntity(mockInput)
        assertNotNull(returnObject)
        assertNotNull(returnObject.request_token)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }
}