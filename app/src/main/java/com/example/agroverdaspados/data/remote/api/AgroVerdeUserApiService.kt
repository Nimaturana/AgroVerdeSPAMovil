package com.example.agroverdaspados.data.remote.api

import com.example.agroverdaspados.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.*

interface AgroVerdeUserApiService {

    // -------- CLIENTE PROFILE --------
    @GET("cliente-profile/me")
    suspend fun getMyClienteProfile(): Response<UserDto>

    @PUT("cliente-profile/me")
    suspend fun updateMyClienteProfile(
        @Body request: UserDto
    ): Response<UserDto>

    @GET("cliente-profile")
    suspend fun getAllClienteProfiles(): Response<List<UserDto>>

    @GET("cliente-profile/{userId}")
    suspend fun getClienteProfileByUserId(
        @Path("userId") userId: String
    ): Response<UserDto>

    // -------- PRODUCTOR PROFILE --------
    @GET("productor-profile/me")
    suspend fun getMyProductorProfile(): Response<UserDto>

    @PUT("productor-profile/me")
    suspend fun updateMyProductorProfile(
        @Body request: UserDto
    ): Response<UserDto>

    @GET("productor-profile")
    suspend fun getAllProductorProfiles(): Response<List<UserDto>>

    @GET("productor-profile/{userId}")
    suspend fun getProductorProfileByUserId(
        @Path("userId") userId: String
    ): Response<UserDto>
}