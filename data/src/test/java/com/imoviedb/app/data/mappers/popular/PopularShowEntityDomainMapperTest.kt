package com.imoviedb.app.data.mappers.popular

import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.popular.mapper.PopularShowEntityModelMapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

class PopularShowEntityDomainMapperTest : BaseMapperTest() {
    //class under test
    private lateinit var popularShowEntityModelMapper: PopularShowEntityModelMapper


    override fun postSetup() {
        popularShowEntityModelMapper = PopularShowEntityModelMapper()
    }

    @Test
    fun `test conversion of entity to model`() {
        //Arrange
        val mockInput = Mockito.mock(ShowEntityModel::class.java)

        //Act
        val returnObject = popularShowEntityModelMapper.map(mockInput)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockInput.id) //Likewise other props can be replicated
    }

    override fun testInstanceOfSubject() {
        //Assertion only
        MatcherAssert.assertThat(
            popularShowEntityModelMapper,
            CoreMatchers.instanceOf(Mapper::class.java)
        )
    }
}