package com.dogsteven.sellingapplication.domain.use_case

import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.domain.model.remote.User
import com.dogsteven.sellingapplication.domain.service.authentication.AuthenticationService
import com.dogsteven.sellingapplication.util.map
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authenticationService: AuthenticationService
) {
    data class Request(
        val username: String,
        val password: String
    )

    data class Response(val user: User)

    suspend fun execute(request: Request): Result<Response> {
        val (username, password) = request
        return authenticationService.authenticate(username, password)
            .map(::Response)
    }
}