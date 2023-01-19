package com.imoviedb.app.data.mappers.authentication

import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateModelEntityMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.domain.authentication.models.AccessTokenValidateDomainModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class AccessTokenValidateModelEntityMapperTest : BaseMapperTest() {

    //Class under test
    private lateinit var accessTokenValidateModelEntityMapper: AccessTokenValidateModelEntityMapper

    @Before
    override fun postSetup() {
        accessTokenValidateModelEntityMapper = AccessTokenValidateModelEntityMapper()
    }

    @Test
    fun `test mapModelToEntity`() {
        //Arrange input
        val mockInput = mock(AccessTokenValidateDomainModel::class.java)

        //Act
        val returnObject = accessTokenValidateModelEntityMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertNotNull(returnObject.request_token)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(accessTokenValidateModelEntityMapper, CoreMatchers.instanceOf(Mapper::class.java))
    }
}