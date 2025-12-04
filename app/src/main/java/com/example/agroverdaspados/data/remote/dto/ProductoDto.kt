package com.example.agroverdaspados.data.remote.dto

data class ProductoDto(
    val id: String,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val productor: String,
    val imagen: String? = null
)