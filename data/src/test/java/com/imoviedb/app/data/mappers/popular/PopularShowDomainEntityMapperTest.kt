package com.imoviedb.app.data.mappers.popular

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.popular.mapper.PopularShowDomainEntityMapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

class PopularShowDomainEntityMapperTest : BaseMapperTest() {

    private lateinit var popularShowDomainEntityMapper: PopularShowDomainEntityMapper

    override fun postSetup() {
        popularShowDomainEntityMapper = PopularShowDomainEntityMapper()
    }

    @Test
    fun `test conversion of model to Db entity`() {
        //Arrange inputs
        val mockInput = Mockito.mock(ShowDomainModel::class.java)

        //Act
        val returnObject = popularShowDomainEntityMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id)
        assertEquals(returnObject.name, mockInput.name)
        assertEquals(returnObject.adult, mockInput.adult)
        assertEquals(returnObject.originalName, mockInput.originalName)
        assertEquals(returnObject.originalTitle, mockInput.originalTitle)
        assertEquals(returnObject.originalLanguage, mockInput.originalLanguage)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(
            popularShowDomainEntityMapper,
            CoreMatchers.instanceOf(Mapper::class.java)
        )
    }
}