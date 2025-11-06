package com.example.agroverdaspados.model

import android.net.Uri

/**
 * Representa el estado de la pantalla de perfil.
 * Usado por ProfileViewModel para actualizar la UI de manera reactiva.
 */

data class ProfileUiState(
    val isLoading: Boolean = true,
    val userName: String = "",
    val userEmail: String = "",
    val avatarUri: Uri? = null
)