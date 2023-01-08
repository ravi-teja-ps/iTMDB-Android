package com.imoviedb.app.domain.account.repository

import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.account.mapper.AccountModelMapper
import com.imoviedb.app.domain.base.BaseDomainTestClass
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Response

class AccountDomainImplTest : BaseDomainTestClass() {

    @Mock
    private lateinit var accountService: AccountService

    @Mock
    private lateinit var accountDAO: AccountDAO

    @Mock
    private lateinit var mapper: AccountModelMapper

    @Mock
    private lateinit var userSessionDAO: UserSessionDAO

    private lateinit var accountRepository: AccountRepository

    override fun onPostSetup() {
        accountRepository =
            AccountRepositoryImpl(accountService, accountDAO, mapper, userSessionDAO)
    }

    @Test
    fun accountRepositoryImplTest_getAccountInfo() {
        runTest {
            val mockedSession = "Zi1ao2i3i"
            val mockedAccountModel = mock(Response::class.java)
            accountRepository.getAccountInfo(mockedSession)
            doReturn(mockedAccountModel).`when`(accountService).account(session_id = mockedSession)
            val result =  accountService.account(session_id = mockedSession)
            assertEquals(result,mockedAccountModel)
        }
    }

    @Test
    fun getStoredSessionId() {
        val mockedSessionEntity = mock(UserSessionEntity::class.java)
        doReturn(mockedSessionEntity).`when`(userSessionDAO).getSession()
        userSessionDAO.getSession()
        verify(userSessionDAO).getSession()
        assertEquals(userSessionDAO.getSession().session_id,mockedSessionEntity.session_id)

    }


}