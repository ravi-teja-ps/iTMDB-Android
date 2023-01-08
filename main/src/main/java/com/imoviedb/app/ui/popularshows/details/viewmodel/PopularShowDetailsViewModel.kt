package com.imoviedb.app.ui.popularshows.details.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.data.di.DispatcherProvider
import com.imoviedb.app.data.networking.utils.ErrorCodes
import com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCase
import com.imoviedb.app.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularShowDetailsViewModel @Inject constructor(private val getPopularShowDetailsUseCase: GetPopularShowDetailsUseCase,
                                                      private val dispatcherProvider: DispatcherProvider) :
    BaseViewModel() {
    private val _data = MutableStateFlow<State>(State.Loading(true))
    val data = _data

    /**
     * Fetch show details from stored items in Popular shows list screen based on show id unique
     */
    fun getShowDetailsFromDB(id: Int) {
        viewModelScope.launch {
            getPopularShowDetailsUseCase.getPopularShowDetails(id,dispatcherProvider.default).catch {
                data.value =State.OnError(ErrorCodes.INTERNAL)
            }.collectLatest {
                data.value = State.OnComplete(it)
            }
        }
    }
}