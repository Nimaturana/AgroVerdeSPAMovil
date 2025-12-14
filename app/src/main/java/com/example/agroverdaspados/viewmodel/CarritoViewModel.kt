package com.example.agroverdaspados.viewmodel

import androidx.lifecycle.ViewModel
import com.example.agroverdaspados.model.CarritoItem
import com.example.agroverdaspados.data.remote.dto.ProductoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarritoViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CarritoItem>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    fun addProducto(producto: ProductoDto) {
        val current = _cartItems.value.toMutableList()

        // busca si el producto ya existe en el carrito
        val index = current.indexOfFirst { it.producto.id == producto.id }

        if (index >= 0) {
            val item = current[index]
            current[index] = item.copy(cantidad = item.cantidad + 1)
        } else {
            current.add(CarritoItem(producto))
        }

        _cartItems.value = current
    }

    // vacia carrito
    fun clearCart() {
        _cartItems.value = emptyList()
    }

    // total precio carrito
    fun total(): Double {
        return _cartItems.value.sumOf {
            it.producto.precio * it.cantidad
        }
    }
}
