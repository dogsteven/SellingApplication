package com.dogsteven.sellingapplication.util

interface ResultHandler<T> {
    suspend fun onSuccess(value: T)
    suspend fun onFailure(reason: String)

    suspend operator fun invoke(result: Result<T>) {
        when (result) {
            is Result.Success<T> -> onSuccess(result.value)
            is Result.Failure<T> -> onFailure(result.reason)
        }
    }
}