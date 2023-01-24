package com.imoviedb.common.state

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()

    data class Error(val code: Int, val message:String?) : ResponseWrapper<Nothing>()
}