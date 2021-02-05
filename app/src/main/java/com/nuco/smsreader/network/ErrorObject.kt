package com.nuco.smsreader.network

import java.io.IOException

class ErrorObject(val code: Int?, val msg: String?) : IOException() {

    override fun toString(): String {
        return "ErrorObject(code=$code, msg=$msg)"
    }

    companion object {
        const val NO_CONNECTION = 0
        const val UNKNOWN = 1
        const val ROOM_EMPTY_RESULT_SET = 2
        const val NO_CONTENT = 204
        const val ERROR_VALIDATION = 400
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val INTERNAL_SERVER_ERROR = 500
    }
}