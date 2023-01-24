package com.imoviedb.app.data.repository.account

import com.imoviedb.app.data.base.BaseDataTestClass
import com.imoviedb.app.data.dto.account.mapper.AccountDtoModelMapper
import com.imoviedb.app.data.dto.account.mapper.AccountModelEntityMapper
import com.imoviedb.app.data.networking.apiservice.AccountService
import com.imoviedb.app.data.storage.account.AccountDAO
import com.imoviedb.app.data.storage.authentication.UserSessionDAO
import com.imoviedb.app.data.storage.authentication.UserSessionEntity
import com.imoviedb.app.domain.account.repository.AccountRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Response


@ExperimentalCoroutinesApi
class AccountRepositoryTest : BaseDataTestClass() {

    @Mock
    private lateinit var accountService: AccountService

    @Mock
    private lateinit var accountDAO: AccountDAO

    @Mock
    private lateinit var dtoModelMapper: AccountDtoModelMapper

    @Mock
    private lateinit var modelEntityMapper: AccountModelEntityMapper

    @Mock
    private lateinit var userSessionDAO: UserSessionDAO

    //The class under test
    private lateinit var accountRepository: AccountRepository

    override fun onPostSetup() {
        accountRepository = AccountRepositoryImpl(
            accountService,
            accountDAO,
            dtoModelMapper,
            modelEntityMapper,
            userSessionDAO,
            dispatcherProvider
        )
    }

    @Test
    fun accountRepositoryImplTest_getAccountInfo() {
        runTest {
            //Arrange
            val mockedSession = "Zi1ao2i3i"
            val mockedAccountModel = mock(Response::class.java)
            doReturn(mockedAccountModel).`when`(accountService)
                .account(session_id = mockedSession) //Stubbing

            //Act
            accountRepository.getAccountInfo(mockedSession)
            val result = accountService.account(session_id = mockedSession)

            //Assertion
            assertEquals(result, mockedAccountModel)
        }
    }

    @Test
    fun getStoredSessionId() {
        //Arrange mock
        val mockedSessionEntity = mock(UserSessionEntity::class.java)
        doReturn(mockedSessionEntity).`when`(userSessionDAO).getSession()

        //Act
        userSessionDAO.getSession()

        //Assertion
        verify(userSessionDAO).getSession()
        assertEquals(userSessionDAO.getSession().sessionId, mockedSessionEntity.sessionId)
    }
}