package com.imoviedb.app.data.dto.account.mapper

import com.imoviedb.app.domain.account.model.AccountDomainModel
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AccountModelEntityMapperTest {

    private  lateinit var accountModelEntityMapper: AccountModelEntityMapper
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        accountModelEntityMapper= AccountModelEntityMapper()
    }


    @Test
    fun mapModelToEntity() {
        val accountDomainModel = mock(AccountDomainModel::class.java)
        val returnObject = accountModelEntityMapper.map(accountDomainModel)
        assertNotNull(returnObject)
        assertEquals(returnObject.id, accountDomainModel.id)
        assertEquals(returnObject.name, accountDomainModel.name)
        assertEquals(returnObject.iso31661, accountDomainModel.iso31661)
        assertEquals(returnObject.username, accountDomainModel.username)
        assertEquals(returnObject.statusMessage, accountDomainModel.statusMessage)
        assertEquals(returnObject.avatarHash, accountDomainModel.avatarHash)

    }
}