package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.domain.account.model.AccountDomainModel
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AccountModelMapperTest {

    private  lateinit var accountModelMapper: AccountModelMapper
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accountModelMapper= AccountModelMapper()
    }

    @Test
    fun mapDtoToDomainModel() {
        val mockAccountDto = mock(AccountDto::class.java)
        val returnObject = accountModelMapper.mapDtoToDomainModel(mockAccountDto)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, mockAccountDto.id)
        assertEquals(returnObject.name, mockAccountDto.name)
        assertEquals(returnObject.iso31661, mockAccountDto.iso31661)
        assertEquals(returnObject.username, mockAccountDto.username)
        assertEquals(returnObject.statusMessage, mockAccountDto.statusMessage)
        assertEquals(returnObject.avatarHash, mockAccountDto.avatarDto?.gravatarDto?.hash)

    }

    @Test
    fun mapModelToEntity() {
        val accountDomainModel = mock(AccountDomainModel::class.java)
        val returnObject = accountModelMapper.mapModelToEntity(accountDomainModel)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, accountDomainModel.id)
        assertEquals(returnObject.name, accountDomainModel.name)
        assertEquals(returnObject.iso31661, accountDomainModel.iso31661)
        assertEquals(returnObject.username, accountDomainModel.username)
        assertEquals(returnObject.statusMessage, accountDomainModel.statusMessage)
        assertEquals(returnObject.avatarHash, accountDomainModel.avatarHash)

    }
}