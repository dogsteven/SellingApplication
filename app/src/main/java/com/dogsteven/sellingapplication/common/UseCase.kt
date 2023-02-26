package com.dogsteven.sellingapplication.common

import com.dogsteven.sellingapplication.util.Result

interface UseCase<Request, Response> {
    suspend fun execute(request: Request): Result<Response>
}