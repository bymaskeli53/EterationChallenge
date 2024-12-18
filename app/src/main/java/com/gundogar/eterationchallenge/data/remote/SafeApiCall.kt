package com.gundogar.eterationchallenge.data.remote

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        val response = apiCall()
        ApiResult.Success(response)
    } catch (e: Exception) {
        ApiResult.Error(
            message = e.localizedMessage ?: "An unexpected error occurred",
            exception = e
        )
    }
}
