package com.imoviedb.app.data.mappers.authentication

import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateModelEntityMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.data.storage.authentication.UserTokenEntity
import com.imoviedb.app.data.utils.mockedAccessTokenValidateDto
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

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
        val mockInput = mockedAccessTokenValidateDto

        //Act
        val returnObject = accessTokenValidateModelEntityMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertThat(returnObject, instanceOf(UserTokenEntity::class.java))
        assertNotNull(returnObject.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    override fun testInstanceOfSubject() {
        assertThat(
            accessTokenValidateModelEntityMapper, instanceOf(Mapper::class.java)
        )
    }
}