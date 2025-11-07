package com.example.agroverdaspados.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para la respuesta de lista de usuarios
 * La API devuelve un objeto con "users" y metadata de paginación
 */

data class UsersResponse(
    @SerializedName("Usuario")
    val users: List<UserDto>,

    @SerializedName("total")
    val total: Int,  // total de usuarios

    @SerializedName("skip")
    val skip: Int,   // cuantos usuarios dieron skip

    @SerializedName("limite")
    val limit: Int   // limite de usuarios por pagina
)