package com.example.fizzbuzz.domain

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode: String? = null
) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: String, errorCode: String? = null, data: T? = null) :
        Result<T>(data, message, errorCode)

    class Loading<T>(data: T? = null) : Result<T>(data)
}