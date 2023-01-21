package com.imoviedb.app.data.repository.popularshows.details

import com.imoviedb.app.data.base.BaseDataTestClass
import com.imoviedb.app.data.dto.popular.mapper.PopularShowEntityModelMapper
import com.imoviedb.app.data.storage.popularshows.PopularShowsDao
import com.imoviedb.app.data.utils.mockedShowEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class PopularShowDetailsRepositoryImplTest : BaseDataTestClass() {

    private val popularShowsDao: PopularShowsDao = mock()
    private val entityModelMapper: PopularShowEntityModelMapper = mock()
    private lateinit var popularShowDetailsRepositoryImpl: PopularShowDetailsRepositoryImpl

    override fun onPostSetup() {
        popularShowDetailsRepositoryImpl =
            PopularShowDetailsRepositoryImpl(popularShowsDao, entityModelMapper, dispatcherProvider)
    }

    @Test
    fun getPopularShowDetails() {
        runTest {
            //Arrange
            val mockStubInput = mockedShowEntity
            doReturn(mockStubInput).`when`(popularShowsDao).fetchShowById(MOCK_SHOW_ID)

            //Act
            val result = popularShowsDao.fetchShowById(MOCK_SHOW_ID)

            //assert
            assertEquals(result, mockStubInput)
        }
    }

    companion object MockInputs {
        const val MOCK_SHOW_ID = 1
    }
}