package com.dogsteven.sellingapplication.util

import com.dogsteven.sellingapplication.common.Result

val<T> Result<T>.unsafeUnwrap: T get() = (this as Result.Success<T>).value

fun<T, R> Result<T>.map(transform: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success<T> -> Result.Success(transform(value))
        is Result.Failure<T> -> Result.Failure(exception)
    }
}

fun<T, R> Result<T>.bind(transform: (T) -> Result<R>): Result<R> {
    return when (this) {
        is Result.Success<T> -> transform(value)
        is Result.Failure<T> -> Result.Failure(exception)
    }
}

fun<T, R> Result<T>.fold(onSuccess: (T) -> R, onFailure: (Throwable) -> R): R {
    return when (this) {
        is Result.Success<T> -> onSuccess(value)
        is Result.Failure<T> -> onFailure(exception)
    }
}