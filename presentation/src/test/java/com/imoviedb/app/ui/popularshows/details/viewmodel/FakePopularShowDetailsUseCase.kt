package com.imoviedb.app.ui.popularshows.details.viewmodel

import com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCase
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel
import com.imoviedb.app.ui.mockedPopularShowsModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePopularShowDetailsUseCase : GetPopularShowDetailsUseCase {

    private val flow = MutableStateFlow(ResponseWrapper.Success(mockedPopularShowsModel))

    override suspend fun getPopularShowDetails(id: Int): Flow<ResponseWrapper<ShowDomainModel>> {
        return flow
    }
}