package com.example.agroverdaspados.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// implementacion persistencia local

private val Context.userDataStore by preferencesDataStore("user_data_store")

class UserDataStore(private val context: Context) {

    companion object {
        private val KEY_NAME = stringPreferencesKey("user_name")
        private val KEY_EMAIL = stringPreferencesKey("user_email")
        private val KEY_PASSWORD = stringPreferencesKey("user_password")
    }

    // guarda los datos del usuario registrado
    suspend fun saveUser(name: String, email: String, password: String) {
        context.userDataStore.edit { prefs ->
            prefs[KEY_NAME] = name
            prefs[KEY_EMAIL] = email
            prefs[KEY_PASSWORD] = password
        }
    }

    //  obtiene nombre
    fun getUserName(): Flow<String?> =
        context.userDataStore.data.map { prefs ->
            prefs[KEY_NAME]
        }

    // obtiene email
    fun getUserEmail(): Flow<String?> =
        context.userDataStore.data.map { prefs ->
            prefs[KEY_EMAIL]
        }

    // obtine contraseña
    fun getUserPassword(): Flow<String?> =
        context.userDataStore.data.map { prefs ->
            prefs[KEY_PASSWORD]
        }

    // elimina datos del usuario
    suspend fun clearUser() {
        context.userDataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
