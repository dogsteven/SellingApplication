package com.dogsteven.sellingapplication.domain.model.remote

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val phone: String,
    val permissions: List<Permission>
) {
    @JsonAdapter(Permission.GsonAdapter::class)
    sealed interface Permission {
        object Administrator : Permission
        object Employee : Permission

        class GsonAdapter: TypeAdapter<Permission>() {
            override fun read(`in`: JsonReader): Permission {
                val string = `in`.nextString()

                return if (string == "administrator") {
                    Administrator
                } else {
                    Employee
                }
            }

            override fun write(out: JsonWriter, value: Permission) {
                val string = when (value) {
                    is Administrator -> "administrator"
                    is Employee -> "employee"
                }
                out.value(string)
            }
        }
    }
}