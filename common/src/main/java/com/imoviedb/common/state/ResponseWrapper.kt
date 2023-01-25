package com.imoviedb.common.state

/**
 * A Repository layer state wrapper for passing result to View model
 */
sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()

    data class Error(val code: Int, val message:String?) : ResponseWrapper<Nothing>()
}