package com.imoviedb.app.presentation.ui.popularshows.details.viewmodel

import androidx.lifecycle.viewModelScope
import com.imoviedb.app.domain.popularshows.details.usecase.GetPopularShowDetailsUseCase
import com.imoviedb.app.presentation.ui.base.BaseViewModel
import com.imoviedb.app.presentation.ui.base.State
import com.imoviedb.app.presentation.ui.utils.ErrorCodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularShowDetailsViewModel @Inject constructor(
    private val getPopularShowDetailsUseCase: GetPopularShowDetailsUseCase
) : BaseViewModel() {
    private val _data = MutableStateFlow<State>(
        State.Loading(
            true
        )
    )
    val data = _data

    /**
     * Fetch show details from stored items in Popular shows list screen based on show id unique
     */
    fun getShowDetailsFromDB(id: Int) {
        viewModelScope.launch {
            getPopularShowDetailsUseCase.getPopularShowDetails(id)
                .catch {
                    data.value = State.OnError(ErrorCodes.INTERNAL)
                }.collectLatest {
                    data.value = State.OnComplete(it)
                }
        }
    }
}