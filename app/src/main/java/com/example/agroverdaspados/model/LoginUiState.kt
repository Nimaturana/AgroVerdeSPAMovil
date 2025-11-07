package com.example.agroverdaspados.model

data class LoginUiState(

    // email que ingresa usuario
    val email: String = "",

    // contraseña que ingresa usuario
    val password: String = "",

    // error para email
    val emailError: String? = null,

    // email para contraseña
    val passwordError: String? = null,

    // icono para saber si esta cargando
    val isLoading: Boolean = false

)