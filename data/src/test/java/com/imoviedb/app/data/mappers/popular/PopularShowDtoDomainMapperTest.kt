package com.imoviedb.app.data.mappers.popular

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.data.dto.popular.mapper.PopularShowDtoDomainMapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

class PopularShowDtoDomainMapperTest : BaseMapperTest() {

    //class under test
    private lateinit var popularShowDtoDomainMapper: PopularShowDtoDomainMapper

    override fun postSetup() {
        popularShowDtoDomainMapper = PopularShowDtoDomainMapper()
    }

    @Test
    fun `test conversion of dto to domain model`() {
        //Arrange
        val mockInput = Mockito.mock(ShowDto::class.java)

        //Act
        val returnObject = popularShowDtoDomainMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
        assertEquals(returnObject.name, mockInput.name)
        assertEquals(returnObject.adult, mockInput.adult)
        assertEquals(returnObject.originalName, mockInput.originalName)
        assertEquals(returnObject.originalTitle, mockInput.originalTitle)
        assertEquals(returnObject.originalLanguage, mockInput.originalLanguage)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(
            popularShowDtoDomainMapper,
            CoreMatchers.instanceOf(Mapper::class.java)
        )
    }
}