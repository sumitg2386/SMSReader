package com.nuco.smsreader.network

import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultWrapper<T>()
    data class Error(val exception: Exception) : ResultWrapper<Nothing>()
    data class GenericError(val code: Int? = null, val error: String? = null) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> ResultWrapper<T>): ResultWrapper<T> =
        try {
            call.invoke()
        } catch (throwable: Throwable) {
            when (throwable) {
                is ErrorObject -> {
                    ResultWrapper.GenericError(throwable.code, throwable.msg)
                }
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorMsg = throwable.message()
                    ResultWrapper.GenericError(
                            code,
                            errorMsg
                    )
                }
                else -> ResultWrapper.Error(
                        IOException(
                                throwable.message,
                                throwable
                        )
                )
            }
        }

enum class ErrorResponses {
    Network,
    Generic
}