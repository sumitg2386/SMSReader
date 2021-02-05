package com.nuco.smsreader.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {

    @Throws(Throwable::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var returnResponse: Response? = null

        try {
            returnResponse = chain.proceed(chain.request())
        } catch (e: Exception) {
            val error = e.message
            Log.e("Error", error ?: "")
            throw ErrorObject(returnResponse?.code, error)
        }
        return returnResponse
    }
}