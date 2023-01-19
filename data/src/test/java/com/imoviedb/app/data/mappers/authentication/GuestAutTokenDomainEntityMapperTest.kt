package com.imoviedb.app.data.mappers.authentication

import com.imoviedb.app.data.dto.authentication.GuestAuthCreateTokenDto
import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenDtoDomainMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

class GuestAutTokenDomainEntityMapperTest : BaseMapperTest() {

    //Class under test
    private lateinit var guestAutTokenDtoDomainMapper: GuestAutTokenDtoDomainMapper

    override fun postSetup() {
        guestAutTokenDtoDomainMapper = GuestAutTokenDtoDomainMapper()
    }

    @Test
    fun `test convertDtoToModel`() {
        //Arrange input
        val mockInput = mock(GuestAuthCreateTokenDto::class.java)

        //Act
        val returnObject = guestAutTokenDtoDomainMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.requestToken, mockInput.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(guestAutTokenDtoDomainMapper, CoreMatchers.instanceOf(Mapper::class.java))
    }
}