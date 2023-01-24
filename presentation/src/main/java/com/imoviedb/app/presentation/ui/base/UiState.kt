package com.imoviedb.app.presentation.ui.base

sealed class UiState<out T> {
    data class Loading(var isLoading: Boolean) : UiState<Nothing>()

    data class OnComplete<out T>(val data: T)  : UiState<T>()

    //Refer to ErrorCode.kt class for error codes handled
    data class OnError(var errorCode: Int, var errorMessage: String? = null) : UiState<Nothing>()

}