package com.imoviedb.app.data.networking.utils

import com.google.gson.Gson
import com.imoviedb.app.data.dto.base.ErrorResponseDto
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

/**
 * Helper extension to convert the error response body and parse to Error DTO
 */
inline fun <reified T> ResponseBody.convertErrorBodyToModel(): T {
    val gson = Gson()
    val errorJson = JSONObject(charStream().readText())
    return gson.fromJson(errorJson.toString(), T::class.java)
}

inline fun <reified T> Response<T>.asErrorModel() :ErrorResponseDto {
    return errorBody()!!.convertErrorBodyToModel()
}

inline fun <reified T>Response<T>.isSuccess() :Boolean {
    return isSuccessful && body() != null
}

