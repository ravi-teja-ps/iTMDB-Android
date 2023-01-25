package com.imoviedb.app.data.networking.utils

class ErrorUtils {

    companion object Error{
        const val SOCKET_TIMEOUT_CODE = -100
        const val IO_EXCEPTION_CODE = -101
        const val HTTP_EXCEPTION_CODE = -102
        const val GENERIC_EXCEPTION_CODE = -99
        const val UNKNOWN_HOST_CODE = 0
        //Error Messages
        const val SOCKET_TIMEOUT_MESSAGE = "Request Timed Out, Please try later"
        const val IO_EXCEPTION_MESSAGE = "Error parsing the request.Sorry "
        const val HTTP_EXCEPTION_MESSAGE = "HTTP Exception"
        const val GENERIC_EXCEPTION_MESSAGE = "Internal Error, Please try later"
        const val UNKNOWN_HOST_EXCEPTION_MESSAGE = "Remote Host or Network Connection unavailable, please try later"
    }
}