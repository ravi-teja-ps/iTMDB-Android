package com.imoviedb.app.presentation.ui.base

import androidx.paging.PagingData
import com.imoviedb.app.domain.base.BaseDomainModel
import com.imoviedb.app.domain.popularshows.models.ShowDomainModel

sealed class State {
    data class Loading(var isLoading: Boolean) : State()

    data class OnComplete(var completionResult: BaseDomainModel) : State()

    //Only for Paging3 screens
    data class OnCompletePagedData(val pagedData: PagingData<ShowDomainModel>) : State()

    //Refer to ErrorCode.kt class for error codes handled
    data class OnError(var errorCode: Int,var errorMessage:String?= null) : State()

}