package com.dogsteven.sellingapplication.common

interface UseCase<Request, Response> {
    suspend fun execute(request: Request): Result<Response>
}