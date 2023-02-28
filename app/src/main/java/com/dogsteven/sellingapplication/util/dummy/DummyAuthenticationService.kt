package com.dogsteven.sellingapplication.util.dummy

import com.dogsteven.sellingapplication.domain.model.remote.User
import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.domain.service.authentication.AuthenticationService
import com.dogsteven.sellingapplication.util.dummy.DummyUserDatabase
import kotlinx.coroutines.delay
import javax.inject.Inject

class DummyAuthenticationService @Inject constructor(
    private val dummyUserDatabase: DummyUserDatabase
): AuthenticationService {
    override suspend fun authenticate(username: String, password: String): Result<User> {
        delay(2000)
        for (user in dummyUserDatabase.users) {
            if (user.username == username && user.password == password) {
                return Result.Success(user)
            }
        }
        return Result.Failure(Throwable("Username or password is incorrect"))
    }
}