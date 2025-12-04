package com.example.agroverdaspados.data.remote.dto

/**
 * Wrapper para errores del API
 */
data class ErrorResponse(
    val success: Boolean = false,
    val message: String,
    val error: String? = null
)