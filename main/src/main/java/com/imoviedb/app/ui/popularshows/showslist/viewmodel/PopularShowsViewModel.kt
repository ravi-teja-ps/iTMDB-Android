package com.imoviedb.app.ui.popularshows.showslist.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.imoviedb.app.domain.popularshows.showslist.usecase.PopularShowsUseCase
import com.imoviedb.app.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class PopularShowsViewModel @Inject constructor(private val popularShowsUseCase: PopularShowsUseCase) : BaseViewModel() {
    private val _data = MutableStateFlow<State>(State.Loading(true))
    val data = _data

    /**
     * fetch popular shows data from repository . Return result to UI back
     */
    fun getPopularShows(){
        viewModelScope.launch {
            popularShowsUseCase.fetchPopularShows().collectLatest{
                _data.value = State.OnCompletePagedData(it)
            }
        }
    }
}
