package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.domain.account.model.AccountDomainModel
import com.imoviedb.app.domain.account.repository.AccountRepository
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*

class GetAccountUseCaseImplTest : BaseDomainTestClass() {

    private lateinit var getAccountUseCaseImpl: GetAccountUseCaseImpl
    private val accountRepository: AccountRepository = mock()

    @Test
    fun getAccountInfo() {
        runBlocking {
            //Arrange

            val mockedDomainModel = mock(AccountDomainModel::class.java)
            doReturn(flowOf(mockedDomainModel)).`when`(accountRepository)
                .getAccountInfo(mockedInput)

            //Act
            getAccountUseCaseImpl.getAccountInfo(mockedInput)

            //Assert or verify
            verify(accountRepository).getAccountInfo(mockedInput)
        }
    }

    override fun postSetup() {
        getAccountUseCaseImpl = GetAccountUseCaseImpl(accountRepository)
    }

    private companion object {
        const val mockedInput = "aaZ1w"
    }
}