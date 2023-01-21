package com.imoviedb.app.data.mappers.authentication

import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AccessTokenValidateDtoModelMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.data.utils.mockedAccessTokenDto
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AccessTokenValidateDtoModelMapperTest : BaseMapperTest() {

    //Class under test
    private lateinit var accessTokenValidateDtoModelMapper: AccessTokenValidateDtoModelMapper

    override fun postSetup() {
        accessTokenValidateDtoModelMapper = AccessTokenValidateDtoModelMapper()
    }

    @Test
    fun `test mapDtoToModel`() {
        //Arrange inputs
        val mockInput = mockedAccessTokenDto

        //Act
        val returnObject = accessTokenValidateDtoModelMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.requestToken, mockInput.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(
            accessTokenValidateDtoModelMapper,
            CoreMatchers.instanceOf(Mapper::class.java)
        )
    }
}