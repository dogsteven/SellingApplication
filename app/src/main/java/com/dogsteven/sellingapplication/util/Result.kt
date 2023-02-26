package com.dogsteven.sellingapplication.util

sealed interface Result<T> {
    data class Success<T>(val value: T): Result<T>
    data class Failure<T>(val exception: Throwable): Result<T>

    fun<R> map(transform: (T) -> R): Result<R> {
        return when (this) {
            is Success<T> -> Success(transform(value))
            is Failure<T> -> Failure(exception)
        }
    }

    fun<R> bind(transform: (T) -> Result<R>): Result<R> {
        return when (this) {
            is Success<T> -> transform(value)
            is Failure<T> -> Failure(exception)
        }
    }

    fun<R> fold(onSuccess: (T) -> R, onFailure: (Throwable) -> R): R {
        return when (this) {
            is Success<T> -> onSuccess(value)
            is Failure<T> -> onFailure(exception)
        }
    }
}