package com.dogsteven.sellingapplication.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dogsteven.sellingapplication.domain.model.remote.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("AppDataStore")
        val USER_KEY = stringPreferencesKey("user")
    }

    val getCurrentUser: Flow<User?> = context.dataStore.data
        .map { preferences ->
            val userString = preferences[USER_KEY]
            if (userString != null) {
                try {
                    Gson().fromJson(userString, User::class.java)
                } catch (exception: Throwable) {
                    null
                }
            } else {
                null
            }
        }

    suspend fun saveUser(user: User) {
        val userString = Gson().toJson(user)
        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = userString
        }
    }

    suspend fun removeUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_KEY)
        }
    }
}