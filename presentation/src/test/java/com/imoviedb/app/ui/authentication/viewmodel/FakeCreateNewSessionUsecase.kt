package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.domain.authentication.models.NewSessionDomainModel
import com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCase
import com.imoviedb.app.ui.mockedSessionDomainModel
import com.imoviedb.common.state.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCreateNewSessionUsecase : CreateNewSessionUseCase {

    override suspend fun createNewSession(validUserAccessToken: HashMap<String, String>): Flow<ResponseWrapper<NewSessionDomainModel>> {
        return flow
    }

    private val flow = MutableStateFlow(ResponseWrapper.Success(mockedSessionDomainModel))
    suspend fun emit(value: NewSessionDomainModel) {
        flow.emit(ResponseWrapper.Success(value))
    }
}