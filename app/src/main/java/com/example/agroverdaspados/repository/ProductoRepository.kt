package com.example.agroverdaspados.repository

import com.example.agroverdaspados.data.remote.dto.ProductoDto
import kotlinx.coroutines.delay

class ProductoRepository {

    suspend fun obtenerProductos(): Result<List<ProductoDto>> {
        return try {

            delay(800) // Simula red

            // definimos todos los productos que habran
            val productos = listOf(

                // verduras
                ProductoDto("1", "Tomate fresco", "Verduras", 1200.0, "Agrícola Don Pepe", null),
                ProductoDto("2", "Lechuga escarola", "Verduras", 900.0, "Huerto San Juan", null),
                ProductoDto("3", "Zanahoria orgánica", "Verduras", 800.0, "Campo Verde", null),
                ProductoDto("4", "Papa chilota", "Verduras", 1500.0, "Agrícola Chiloé", null),
                ProductoDto("5", "Cebolla morada", "Verduras", 1100.0, "Huerto Doña Marta", null),
                ProductoDto("6", "Ajo orgánico", "Verduras", 1800.0, "Campo Alto Bio", null),

                // frutas
                ProductoDto("7", "Manzana roja premium", "Frutas", 1500.0, "Frutícola Los Andes", null),
                ProductoDto("8", "Palta Hass", "Frutas", 2500.0, "Agrícola Santa Teresa", null),
                ProductoDto("9", "Plátano orgánico", "Frutas", 1300.0, "Tierra Viva", null),
                ProductoDto("10", "Frutilla fresca", "Frutas", 2800.0, "Granja El Alba", null),
                ProductoDto("11", "Naranja jugosa", "Frutas", 1000.0, "Frutales del Sur", null),

                // hierbas
                ProductoDto("12", "Cilantro orgánico", "Hierbas", 700.0, "Huerto La Esperanza", null),
                ProductoDto("13", "Perejil orgánico", "Hierbas", 650.0, "Campo Verde Sur", null),
                ProductoDto("14", "Albahaca fresca", "Hierbas", 950.0, "Hierbas del Valle", null),

                // legumbres
                ProductoDto("15", "Porotos negros orgánicos", "Legumbres", 2200.0, "EcoLegumbres", null),

                // granos
                ProductoDto("16", "Arroz integral", "Granos", 1800.0, "Organik Food", null),
                ProductoDto("17", "Quínoa premium", "Granos", 3500.0, "Altiplano BioFoods", null),

                // otros (productos elaborados)
                ProductoDto("18", "Aceite de oliva extra virgen", "Otros", 6500.0, "Olivares La Viña", null),
                ProductoDto("19", "Miel orgánica pura", "Otros", 4200.0, "Apícola El Bosque", null),
                ProductoDto("20", "Harina integral orgánica", "Otros", 1700.0, "Molino Verde", null),
            )

            Result.success(productos)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
