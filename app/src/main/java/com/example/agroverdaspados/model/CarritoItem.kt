package com.example.agroverdaspados.model

import com.example.agroverdaspados.data.remote.dto.ProductoDto

data class CarritoItem(
    val producto: ProductoDto,
    val cantidad: Int = 1
)
