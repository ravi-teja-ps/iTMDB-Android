package com.imoviedb.app.data.mappers.authentication


import com.imoviedb.app.data.dto.authentication.mapper.accesstoken.AuthenticationBodyMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.dto.utils.KeyUtils
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.domain.account.model.AuthenticationBody
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class AuthenticationBodyMapperTest : BaseMapperTest() {

    private lateinit var mapper: AuthenticationBodyMapper

    override fun postSetup() {
        mapper = AuthenticationBodyMapper()
    }

    @Test
    fun `test the mapper function with given response returns hashmap of string key value`() {
        //Arrange
        val mockedInput = mock(AuthenticationBody::class.java)

        // Act
        val result = mapper.map(mockedInput)

        //Assert
        Assert.assertNotNull(result)
        Assert.assertEquals(result[KeyUtils.USERNAME_KEY], mockedInput.userName)
        Assert.assertEquals(result[KeyUtils.PASSWORD_KEY], mockedInput.password)
        Assert.assertEquals(result[KeyUtils.REQUEST_TOKEN_KEY], mockedInput.request_token)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(mapper, CoreMatchers.instanceOf(Mapper::class.java))
    }
}