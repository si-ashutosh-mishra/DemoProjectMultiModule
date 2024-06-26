package com.example.base.helper

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()

    data class GenericError(val code: Int?, val message: String?) : ApiResult<Nothing>()

    data class NetworkError(val message: String?) : ApiResult<Nothing>()

}