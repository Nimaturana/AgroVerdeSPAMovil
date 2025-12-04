package com.example.agroverdaspados.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Response de autenticación de agro
 * Retornado por login y register
 */
data class AuthAgroVerdeData(
    val user: UserDto,
    @SerializedName("access_token")
    val accessToken: String
)