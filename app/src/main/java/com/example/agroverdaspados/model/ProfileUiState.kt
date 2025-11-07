package com.example.agroverdaspados.model

import android.net.Uri

data class ProfileUiState(
    // saber si pantalla esta cargando datos
    val isLoading: Boolean = true,

    // nombre de usuario mostrado en perfil guardado en DataStore
    val userName: String = "",

    // Correo electronico guardado en DataStore
    val userEmail: String = "",

    // imagen seleccionada o tomada por la camara emulada
    val avatarUri: Uri? = null
)