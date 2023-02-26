package com.dogsteven.sellingapplication.util.dummy

import com.dogsteven.sellingapplication.domain.model.remote.User
import javax.inject.Inject

class DummyUserDatabase @Inject constructor() {
    val users: ArrayList<User> = arrayListOf(
        User(
            id = 0,
            username = "dogsteven",
            password = "12345678",
            firstname = "Khoa",
            lastname = "Huynh Bach",
            phone = "0972875800",
            permissions = listOf(User.Permission.Administrator)
        ),
        User(
            id = 1,
            username = "khoahuynhbach",
            password = "12345678",
            firstname = "Khoa",
            lastname = "Huynh Bach",
            phone = "0972875800",
            permissions = listOf(User.Permission.Employee)
        )
    )
}