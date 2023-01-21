package com.imoviedb.app.domain.authentication.normaluser.usecase


import app.cash.turbine.test
import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.app.domain.authentication.normaluser.repository.LoginRepository
import com.imoviedb.app.domain.base.BaseDomainTestClass
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)

class CreateNewSessionUseCaseImplTest : BaseDomainTestClass() {

    private lateinit var createNewSessionUseCase : CreateNewSessionUseCaseImpl
    private val loginRepository: LoginRepository = mock()

    override fun postSetup() {
        createNewSessionUseCase = CreateNewSessionUseCaseImpl(loginRepository)
    }

    @Test
    fun `test flow for creating a new session`() {
        runTest {
            //Arrange
            val accessTokenMockMap = HashMap<String,String>().apply { put("","") }
            val sessionDomainModelMock = mock(NewSessionDomainModel::class.java)
            Mockito.doReturn(flowOf(sessionDomainModelMock)).`when`(loginRepository).createNewSessionIDForUser(accessTokenMockMap)

            //Act
            createNewSessionUseCase.createNewSession(accessTokenMockMap).test {

               //Assertion
                assertEquals(awaitItem(),sessionDomainModelMock)
                cancelAndConsumeRemainingEvents()
                verify(loginRepository).createNewSessionIDForUser(accessTokenMockMap)
            }
        }
    }
}