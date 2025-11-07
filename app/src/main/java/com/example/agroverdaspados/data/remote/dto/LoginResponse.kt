package com.example.agroverdaspados.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para la respuesta de login
 * Datos que RECIBIMOS del servidor tras login exitoso
 */
data class LoginResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("Usuario")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("Nombre")
    val firstName: String,

    @SerializedName("Apellido")
    val lastName: String,

    @SerializedName("AccesoToken")
    val accessToken: String,  // TOKEN JWT

    @SerializedName("RefrescarToken")
    val refreshToken: String?  // opcional - Para renovar el token
)