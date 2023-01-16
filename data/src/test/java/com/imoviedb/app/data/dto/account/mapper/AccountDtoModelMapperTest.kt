package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.domain.account.model.AccountDomainModel
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AccountDtoModelMapperTest {

    private  lateinit var accountDtoModelMapper: AccountDtoModelMapper
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accountDtoModelMapper= AccountDtoModelMapper()
    }

    @Test
    fun mapDtoToDomainModel() {
        val mockAccountDto = mock(AccountDto::class.java)
        val returnObject = accountDtoModelMapper.map(mockAccountDto)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockAccountDto.id)
        assertEquals(returnObject.name, mockAccountDto.name)
        assertEquals(returnObject.iso31661, mockAccountDto.iso31661)
        assertEquals(returnObject.username, mockAccountDto.username)
        assertEquals(returnObject.statusMessage, mockAccountDto.statusMessage)
        assertEquals(returnObject.avatarHash, mockAccountDto.avatarDto?.gravatarDto?.hash)

    }
}