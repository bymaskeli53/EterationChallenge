package com.gundogar.eterationchallenge.data.remote

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String, val exception: Throwable? = null) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}
