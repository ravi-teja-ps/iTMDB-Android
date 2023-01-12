package com.imoviedb.app.domain.account.usecase

import com.imoviedb.app.data.dto.account.AccountDto
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

class GetAccountUseCaseImplTest :BaseDomainTestClass(){

    @Mock
    private lateinit var fakeGetAccountUseCaseRepository:FakeGetAccountUseCaseRepository

    private lateinit var getAccountUseCase : GetAccountUseCase

    @Before
    override fun onPostSetup() {
        getAccountUseCase = GetAccountUseCaseImpl(fakeGetAccountUseCaseRepository)
    }

    @Test
    fun `test getAccountUseCase for given sessionId should fetch account info`() {
        runTest {
            val mockSEssionId = "Z828k11kakaj"
            val mockAccountModel = mock(AccountDto::class.java)
            doReturn(flowOf(mockAccountModel)).`when`(fakeGetAccountUseCaseRepository).getAccountInfo(sessionId = mockSEssionId)

            getAccountUseCase.getAccountInfo(mockSEssionId).collect{
                Assert.assertEquals(it,mockAccountModel)
            }
            fakeGetAccountUseCaseRepository.emit(mockAccountModel)
        }
    }

}