package com.imoviedb.app.data.mappers.account

import com.imoviedb.app.data.dto.account.mapper.AccountModelEntityMapper
import com.imoviedb.app.data.dto.base.mapper.Mapper
import com.imoviedb.app.data.mappers.BaseMapperTest
import com.imoviedb.app.domain.account.model.AccountDomainModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

class AccountModelEntityMapperTest : BaseMapperTest() {

    private lateinit var accountModelEntityMapper: AccountModelEntityMapper

    override fun postSetup() {
        accountModelEntityMapper = AccountModelEntityMapper()
    }

    @Test
    fun `test the Model to Entity mapping`() {
        //Arrange the inputs
        val accountDomainModel = mock(AccountDomainModel::class.java)

        //Act
        val returnObject = accountModelEntityMapper.map(accountDomainModel)

        //Assertion
        assertNotNull(returnObject)
        assertEquals(returnObject.id, accountDomainModel.id)
        assertEquals(returnObject.name, accountDomainModel.name)
        assertEquals(returnObject.iso31661, accountDomainModel.iso31661)
        assertEquals(returnObject.username, accountDomainModel.username)
        assertEquals(returnObject.statusMessage, accountDomainModel.statusMessage)
        assertEquals(returnObject.avatarHash, accountDomainModel.avatarHash)
    }

    override fun testInstanceOfSubject() {
        MatcherAssert.assertThat(
            accountModelEntityMapper,
            CoreMatchers.instanceOf(Mapper::class.java)
        )
    }
}