package com.example.agroverdaspados.data.remote.api

import com.example.agroverdaspados.data.remote.dto.ProductoDto
import retrofit2.Response
import retrofit2.http.GET

interface AgroVerdeProductoApiService {

    // GET /producto-agricola
    @GET("producto-agricola")
    suspend fun getProductos(): Response<List<ProductoDto>>

}
