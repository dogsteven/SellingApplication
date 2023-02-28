package com.dogsteven.sellingapplication.util.dummy

import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.domain.model.remote.User
import com.dogsteven.sellingapplication.domain.repository.remote.user.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class DummyUserRepository @Inject constructor(
    private val dummyUserDatabase: DummyUserDatabase
): UserRepository {
    override suspend fun getUserByID(id: Int): Result<User> {
        delay(2000)
        for (user in dummyUserDatabase.users) {
            if (user.id == id) {
                return Result.Success(user)
            }
        }
        return Result.Failure(Throwable("No user with id $id"))
    }

    override suspend fun getAllUsers(): Result<List<User>> {
        delay(2000)
        return Result.Success(dummyUserDatabase.users)
    }

    override suspend fun createUser(
        username: String,
        password: String,
        firstname: String,
        lastname: String,
        phone: String,
        permissions: List<User.Permission>
    ): Result<User> {
        delay(2000)
        if (dummyUserDatabase.users.any { user -> user.username == username }) {
            return Result.Failure(Throwable("User with username $username does exists"))
        } else {
            val id = (dummyUserDatabase.users.map { it.id }.maxOrNull() ?: -1) + 1
            val user = User(
                id = id,
                username = username,
                password = password,
                firstname = firstname,
                lastname = lastname,
                phone = phone,
                permissions = permissions
            )
            dummyUserDatabase.users.add(user)

            return Result.Success(user)
        }
    }

    override suspend fun updateUser(
        id: Int,
        password: String,
        firstname: String,
        lastname: String,
        phone: String,
        permissions: List<User.Permission>
    ): Result<User> {
        for (i in 0 until dummyUserDatabase.users.size) {
            if (dummyUserDatabase.users[i].id == id) {
                dummyUserDatabase.users[i] = dummyUserDatabase.users[i].copy(
                    password = password,
                    firstname = firstname,
                    lastname = lastname,
                    phone = phone,
                    permissions = permissions
                )

                return Result.Success(dummyUserDatabase.users[i])
            }
        }
        return Result.Failure(Throwable("No user with id $id"))
    }

    override suspend fun removeUser(id: Int): Result<User> {
        for (i in 0 until dummyUserDatabase.users.size) {
            if (dummyUserDatabase.users[i].id == id) {
                val user = dummyUserDatabase.users[i]
                dummyUserDatabase.users.removeAt(i)
                return Result.Success(user)
            }
        }
        return Result.Failure(Throwable("No user with id $id"))
    }
}