package com.imoviedb.app.data.networking.utils

import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject

inline fun <reified T> ResponseBody.toErrorModel(): T {
    val gson = Gson()
    val errorJson = JSONObject(charStream().readText())
    return gson.fromJson(errorJson.toString(), T::class.java)
}