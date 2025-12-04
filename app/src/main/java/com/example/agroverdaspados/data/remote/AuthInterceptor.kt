package com.example.agroverdaspados.data.remote

import com.example.agroverdaspados.data.local.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/**
 * AuthInterceptor: Añade automáticamente el token JWT a las peticiones
 *
 * ¿Cuándo se ejecuta?
 * - ANTES de cada petición HTTP
 *
 * ¿Qué hace?
 * 1. Recupera el token del SessionManager
 * 2. Si existe, añade el header: Authorization: Bearer {token}
 * 3. Si no existe, deja la petición sin modificar
 */
class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        // ENDPOINTS PÚBLICOS (sin token)
        val endpointsPublicos = listOf(
            "/producto-agricola"
        )

        if (endpointsPublicos.any { url.contains(it) }) {
            return chain.proceed(originalRequest)
        }

        // cargar token
        val token = runBlocking {
            sessionManager.getAuthToken()
        }

        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
