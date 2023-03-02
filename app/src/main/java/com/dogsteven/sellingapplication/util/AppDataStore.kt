package com.dogsteven.sellingapplication.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.dogsteven.sellingapplication.domain.model.remote.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {
    private class SavedSignInFieldKeys(
        val USERNAME: Preferences.Key<String> = stringPreferencesKey("SAVED_SIGN_IN_FIELD/USERNAME"),
        val PASSWORD: Preferences.Key<String> = stringPreferencesKey("SAVED_SIGN_IN_FIELD/PASSWORD")
    )

    private class UserProfileKeys(
        val ID: Preferences.Key<Int> = intPreferencesKey("USER_PROFILE/ID"),
        val USERNAME: Preferences.Key<String> = stringPreferencesKey("USER_PROFILE/USERNAME"),
        val PASSWORD: Preferences.Key<String> = stringPreferencesKey("USER_PROFILE/PASSWORD"),
        val FIRSTNAME: Preferences.Key<String> = stringPreferencesKey("USER_PROFILE/FIRSTNAME"),
        val LASTNAME: Preferences.Key<String> = stringPreferencesKey("USER_PROFILE/LASTNAME"),
        val PHONE: Preferences.Key<String> = stringPreferencesKey("USER_PROFILE/PHONE"),
        val PERMISSIONS: Preferences.Key<Set<String>> = stringSetPreferencesKey("USER_PROFILE/PERMISSIONS")
    )

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("AppDataStore")
        private val SAVED_SIGN_IN_FIELD_KEYS = SavedSignInFieldKeys()
        private val USER_PROFILE_KEYS = UserProfileKeys()
    }

    val currentSavedUsernameAndPassword: Flow<Pair<String, String>?> = context.dataStore.data
        .map { preferences ->
            val savedUsername = if (preferences.contains(SAVED_SIGN_IN_FIELD_KEYS.USERNAME)) {
                preferences[SAVED_SIGN_IN_FIELD_KEYS.USERNAME]!!
            } else {
                return@map null
            }

            val savedPassword = if (preferences.contains(SAVED_SIGN_IN_FIELD_KEYS.PASSWORD)) {
                preferences[SAVED_SIGN_IN_FIELD_KEYS.PASSWORD]!!
            } else {
                return@map  null
            }

            Pair(savedUsername, savedPassword)
        }

    suspend fun saveSavedUsernameAndPassword(username: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[SAVED_SIGN_IN_FIELD_KEYS.USERNAME] = username
            preferences[SAVED_SIGN_IN_FIELD_KEYS.PASSWORD] = password
        }
    }

    suspend fun removeSavedUsernameAndPassword() {
        context.dataStore.edit { preferences ->
            preferences.remove(SAVED_SIGN_IN_FIELD_KEYS.USERNAME)
            preferences.remove(SAVED_SIGN_IN_FIELD_KEYS.PASSWORD)
        }
    }

    val currentUser: Flow<User?> = context.dataStore.data
        .map { preferences ->
            val id = if (preferences.contains(USER_PROFILE_KEYS.ID)) {
                preferences[USER_PROFILE_KEYS.ID]!!
            } else {
                return@map null
            }

            val username = if (preferences.contains(USER_PROFILE_KEYS.USERNAME)) {
                preferences[USER_PROFILE_KEYS.USERNAME]!!
            } else {
                return@map null
            }

            val password = if (preferences.contains(USER_PROFILE_KEYS.PASSWORD)) {
                preferences[USER_PROFILE_KEYS.PASSWORD]!!
            } else {
                return@map null
            }

            val firstname = if (preferences.contains(USER_PROFILE_KEYS.FIRSTNAME)) {
                preferences[USER_PROFILE_KEYS.FIRSTNAME]!!
            } else {
                return@map null
            }

            val lastname = if (preferences.contains(USER_PROFILE_KEYS.LASTNAME)) {
                preferences[USER_PROFILE_KEYS.LASTNAME]!!
            } else {
                return@map null
            }

            val phone = if (preferences.contains(USER_PROFILE_KEYS.PHONE)) {
                preferences[USER_PROFILE_KEYS.PHONE]!!
            } else {
                return@map null
            }

            val permissions = if (preferences.contains(USER_PROFILE_KEYS.PERMISSIONS)) {
                preferences[USER_PROFILE_KEYS.PERMISSIONS]!!.map { permission ->
                    when (permission) {
                        "administrator" -> User.Permission.Administrator
                        "employee" -> User.Permission.Employee
                        else -> User.Permission.Employee
                    }
                }
            } else {
                return@map null
            }

            User(id, username, password, firstname, lastname, phone, permissions)
        }

    suspend fun saveUser(user: User) {
        val (id, username, password, firstname, lastname, phone, permissions) = user
        context.dataStore.edit { preferences ->
            preferences[USER_PROFILE_KEYS.ID] = id
            preferences[USER_PROFILE_KEYS.USERNAME] = username
            preferences[USER_PROFILE_KEYS.PASSWORD] = password
            preferences[USER_PROFILE_KEYS.FIRSTNAME] = firstname
            preferences[USER_PROFILE_KEYS.LASTNAME] = lastname
            preferences[USER_PROFILE_KEYS.PHONE] = phone
            preferences[USER_PROFILE_KEYS.PERMISSIONS] = permissions.map { permission ->
                when (permission) {
                    is User.Permission.Administrator -> "administrator"
                    is User.Permission.Employee -> "employee"
                }
            }.toSet()
        }
    }

    suspend fun removeUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_PROFILE_KEYS.ID)
            preferences.remove(USER_PROFILE_KEYS.USERNAME)
            preferences.remove(USER_PROFILE_KEYS.PASSWORD)
            preferences.remove(USER_PROFILE_KEYS.FIRSTNAME)
            preferences.remove(USER_PROFILE_KEYS.LASTNAME)
            preferences.remove(USER_PROFILE_KEYS.PHONE)
            preferences.remove(USER_PROFILE_KEYS.PERMISSIONS)
        }
    }
}