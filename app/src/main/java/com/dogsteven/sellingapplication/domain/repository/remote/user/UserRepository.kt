package com.dogsteven.sellingapplication.domain.repository.remote.user

import com.dogsteven.sellingapplication.util.Result
import com.dogsteven.sellingapplication.domain.model.remote.User

interface UserRepository {
    suspend fun getUserByID(id: Int): Result<User>

    suspend fun getAllUsers(): Result<List<User>>

    suspend fun createUser(
        username: String,
        password: String,
        firstname: String,
        lastname: String,
        phone: String,
        permissions: List<User.Permission>
    ): Result<User>

    suspend fun updateUser(
        id: Int,
        password: String,
        firstname: String,
        lastname: String,
        phone: String,
        permissions: List<User.Permission>
    ): Result<User>

    suspend fun removeUser(id: Int): Result<User>
}