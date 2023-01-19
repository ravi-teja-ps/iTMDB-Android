package com.imoviedb.app.data.mappers.authentication

import com.imoviedb.app.data.dto.authentication.mapper.guesttoken.GuestAutTokenModelEntityMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.domain.authentication.models.GuestAuthCreateTokenDomainModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

class GuestAutTokenDtoDomainMapperTest : BaseMapperTest() {

    //Class under test
    private lateinit var guestAutTokenModelEntityMapper: GuestAutTokenModelEntityMapper

    override fun postSetup() {
        guestAutTokenModelEntityMapper = GuestAutTokenModelEntityMapper()
    }

    @Test
    fun `test convertModelToEntity`() {
        //Arrange input
        val mockInput = mock(GuestAuthCreateTokenDomainModel::class.java)

        //Act
        val returnObject = guestAutTokenModelEntityMapper.map(mockInput)

        //Assertion and result validation
        assertNotNull(returnObject)
        assertNotNull(returnObject.requestToken)
        assertEquals(returnObject.success, mockInput.success)
        assertEquals(returnObject.expiresAt, mockInput.expiresAt)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(
            guestAutTokenModelEntityMapper,
            CoreMatchers.instanceOf(Mapper::class.java)
        )
    }
}