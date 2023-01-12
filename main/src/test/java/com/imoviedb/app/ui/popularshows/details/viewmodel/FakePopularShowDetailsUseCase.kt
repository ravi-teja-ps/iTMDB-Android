package com.imoviedb.app.ui.popularshows.details.viewmodel

import com.imoviedb.app.data.dto.popular.ShowDto
import com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePopularShowDetailsUseCase : GetPopularShowDetailsUseCase {

    private val flow = MutableStateFlow(ShowDto())
    suspend fun emit(value: ShowDto) = flow.emit(value)

    override suspend fun getPopularShowDetails(
        id: Int,
        dispatcher: CoroutineDispatcher
    ): Flow<ShowDto> {
        return flow
    }
}