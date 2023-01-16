package com.imoviedb.app.data.networking.utils

import com.imoviedb.app.data.BuildConfig

/**
 * API service utils to provide urls and common api keys. API keys are fetched from local.props
 */
class ApiServiceUtils {
    /**fetching url based on flavour and
     *fetching api based on local.properties files. If its jenkins we can configure
     *  it else where in constants too
     **/
    companion object{
        const val BASE_URL  = BuildConfig.BASE_URL_PREFIX
        const val API_KEY_V3 = BuildConfig.API_KEY
    }
}