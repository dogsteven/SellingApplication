package com.dogsteven.sellingapplication.common

sealed interface Result<T> {
    data class Success<T>(val value: T): Result<T>
    data class Failure<T>(val exception: Throwable): Result<T>
}