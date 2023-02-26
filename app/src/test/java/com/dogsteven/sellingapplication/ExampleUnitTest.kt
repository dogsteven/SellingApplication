package com.dogsteven.sellingapplication

import com.dogsteven.sellingapplication.domain.model.remote.User
import com.dogsteven.sellingapplication.cryptography.AESCryptography
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun gsonTest() {
        val gson = Gson()
        val obj = mapOf(
            "salt" to "this is salt",
            "iv" to "this is iv",
            "hint" to mapOf(
                "scheme" to "username",
                "username" to "dogsteven"
            ),
            "message" to "this is encrypted message"
        )

        println(gson.toJson(obj))
    }
}