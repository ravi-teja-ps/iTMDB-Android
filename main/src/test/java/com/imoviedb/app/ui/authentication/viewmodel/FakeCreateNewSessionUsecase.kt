package com.imoviedb.app.ui.authentication.viewmodel

import com.imoviedb.app.data.models.authentication.NewSessionModel
import com.imoviedb.app.domain.authentication.normaluser.usecase.CreateNewSessionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCreateNewSessionUsecase : CreateNewSessionUseCase{

    override suspend fun createNewSession(validUserAccessToken: HashMap<String, String>): Flow<NewSessionModel> {
        return flow
    }

    private val flow = MutableStateFlow(NewSessionModel())
    suspend fun emit(value: NewSessionModel) = flow.emit(value)
}