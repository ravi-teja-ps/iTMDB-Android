package com.imoviedb.app.data.networking.utils

import com.imoviedb.common.state.ResponseWrapper
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//Function to get the error code for exception and show appropriate message.
//Based on code the UI view model can still extend features and write their logic if needed
fun Exception.toResponseWrapper() : ResponseWrapper.Error {
    return when(this@toResponseWrapper){
        is UnknownHostException ->{
            ResponseWrapper.Error(
                ErrorUtils.UNKNOWN_HOST_CODE,
                ErrorUtils.UNKNOWN_HOST_EXCEPTION_MESSAGE
            )
        }
        is SocketTimeoutException -> {
            ResponseWrapper.Error(ErrorUtils.SOCKET_TIMEOUT_CODE, ErrorUtils.SOCKET_TIMEOUT_MESSAGE)
        }
        is IOException ->{
            ResponseWrapper.Error(ErrorUtils.IO_EXCEPTION_CODE, ErrorUtils.IO_EXCEPTION_MESSAGE)
        }
        is HttpException ->{
            ResponseWrapper.Error(ErrorUtils.HTTP_EXCEPTION_CODE, ErrorUtils.HTTP_EXCEPTION_MESSAGE)
        }
        else -> {
            ResponseWrapper.Error(
                ErrorUtils.GENERIC_EXCEPTION_CODE,
                ErrorUtils.GENERIC_EXCEPTION_MESSAGE
            )
        }
    }
}