package com.example.agroverdaspados.data.remote.api

import com.example.agroverdaspados.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface AgroVerdeAuthApiService {

    // POST /api/auth/register
    @POST("auth/register")
    suspend fun register(
        @Body request: LoginRequest   // si tienes otro request, lo cambiamos
    ): Response<AuthAgroVerdeData>

    // POST /api/auth/login
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthAgroVerdeData>

    // GET /api/auth/profile
    @GET("auth/profile")
    suspend fun getProfile(): Response<UserDto>

    // GET /api/auth/users (admin)
    @GET("auth/users")
    suspend fun getAllUsers(): Response<List<UserDto>>
}
