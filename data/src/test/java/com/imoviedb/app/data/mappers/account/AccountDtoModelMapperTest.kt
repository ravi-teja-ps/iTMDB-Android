package com.imoviedb.app.data.mappers.account

import com.imoviedb.app.data.dto.account.mapper.AccountDtoModelMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.data.utils.mockedAccountDto
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AccountDtoModelMapperTest : BaseMapperTest() {

    //Class under test
    private lateinit var accountDtoModelMapper: AccountDtoModelMapper

    override fun postSetup() {
        accountDtoModelMapper = AccountDtoModelMapper()
    }

    @Test
    fun mapDtoToDomainModel() {
        //Arrange mock
        val mockAccountDto = mockedAccountDto

        //Act
        val returnObject = accountDtoModelMapper.map(mockAccountDto)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockAccountDto.id)
        assertEquals(returnObject.name, mockAccountDto.name)
        assertEquals(returnObject.iso31661, mockAccountDto.iso31661)
        assertEquals(returnObject.username, mockAccountDto.username)
        assertEquals(returnObject.statusMessage, mockAccountDto.statusMessage)
        assertEquals(returnObject.avatarHash, mockAccountDto.avatarDto?.gravatarDto?.hash)
    }

    override fun testInstanceOfSubject() {
        assertThat(accountDtoModelMapper, instanceOf(Mapper::class.java))
    }
}