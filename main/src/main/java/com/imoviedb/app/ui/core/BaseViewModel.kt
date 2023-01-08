package com.imoviedb.app.ui.core

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.imoviedb.app.data.models.BaseResponseModel
import com.imoviedb.app.data.storage.popularshows.ShowEntityModel

open class BaseViewModel : ViewModel() {

    /**
     * State holder for all fragments driven by View model  State/Shared Flow's in the app
     */
    sealed class State {

        data class Loading(var isLoading: Boolean) : State()

        data class OnComplete(var completionResult: BaseResponseModel) : State()

        //Only for Paging3 screens
        data class OnCompletePagedData(val pagedData: PagingData<ShowEntityModel>) : State()

        //Refer to ErrorCode.kt class for error codes handled
        data class OnError(var errorCode: Int) : State()
    }
}