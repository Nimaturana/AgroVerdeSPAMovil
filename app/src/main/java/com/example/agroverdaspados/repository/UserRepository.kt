package com.example.agroverdaspados.repository

import com.example.agroverdaspados.data.remote.ApiService
import com.example.agroverdaspados.data.remote.dto.UserDto
import android.content.Context
import com.example.agroverdaspados.data.remote.RetrofitClient



// del repository abtra fuete de datos


class UserRepository(context: Context) {


    // Crear la instancia del API Service (pasando el contexto)

    private val apiService: ApiService = RetrofitClient
        .create(context)
        .create(ApiService::class.java)


    // obtiene un usuario de api

    suspend fun fetchUser(id: Int = 1): Result<UserDto> {
        return try {
            // llama la API
            val user = apiService.getUserById(id)

            // retorna exito
            Result.success(user)

        } catch (e: Exception) {

            // por si algo falla por ejemplo internet
            Result.failure(e)
        }
    }
}