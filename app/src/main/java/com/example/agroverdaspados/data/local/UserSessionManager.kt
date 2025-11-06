package com.example.agroverdaspados.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userSessionDataStore by preferencesDataStore("user_session")

class UserSessionManager(private val context: Context) {

    companion object {
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
    }

    // Guarda el correo electrónico del usuario
    suspend fun saveUserEmail(email: String) {
        context.userSessionDataStore.edit { prefs ->
            prefs[KEY_USER_EMAIL] = email
        }
    }

    // Lee el correo guardado
    fun getUserEmail(): Flow<String?> {
        return context.userSessionDataStore.data.map { prefs ->
            prefs[KEY_USER_EMAIL]
        }
    }

    // Limpia la sesión (para logout)
    suspend fun clearSession() {
        context.userSessionDataStore.edit { it.clear() }
    }
}