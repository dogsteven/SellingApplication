package com.dogsteven.sellingapplication.domain.service.authentication

import com.dogsteven.sellingapplication.domain.model.remote.User
import com.dogsteven.sellingapplication.util.Result

interface AuthenticationService {
    suspend fun authenticate(username: String, password: String): Result<User>
}