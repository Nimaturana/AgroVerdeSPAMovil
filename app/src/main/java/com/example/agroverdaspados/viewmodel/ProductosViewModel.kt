package com.example.agroverdaspados.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.remote.dto.ProductoDto
import com.example.agroverdaspados.repository.ProductoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductosViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _productos = MutableStateFlow<List<ProductoDto>>(emptyList())
    val productos = _productos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    // sistema de filtros
    private val _nombreFiltro = MutableStateFlow("")
    val nombreFiltro = _nombreFiltro.asStateFlow()

    private val _categoriaFiltro = MutableStateFlow("")
    val categoriaFiltro = _categoriaFiltro.asStateFlow()

    private val _precioMin = MutableStateFlow<Float?>(null)
    val precioMin = _precioMin.asStateFlow()

    private val _precioMax = MutableStateFlow<Float?>(null)
    val precioMax = _precioMax.asStateFlow()

    init {
        cargarProductos()
    }

    // carga los productos def en repository
    // al screen
    fun cargarProductos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.obtenerProductos()

            result.fold(
                onSuccess = { lista ->
                    _productos.value = lista
                    _error.value = null
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "Error al cargar productos"
                    _productos.value = emptyList()
                }
            )

            _isLoading.value = false
        }
    }

    fun actualizarNombre(nombre: String) {
        _nombreFiltro.value = nombre
    }

    fun actualizarCategoria(categoria: String) {
        _categoriaFiltro.value = categoria
    }

    fun actualizarPrecioMin(valor: String) {
        _precioMin.value = valor.toFloatOrNull()
    }

    fun actualizarPrecioMax(valor: String) {
        _precioMax.value = valor.toFloatOrNull()
    }

    fun limpiarFiltros() {
        _nombreFiltro.value = ""
        _categoriaFiltro.value = ""
        _precioMin.value = null
        _precioMax.value = null
    }

    // lista de categorias disponibles
    val categoriasDisponibles: StateFlow<List<String>> = productos.map { lista ->
        lista.map { it.categoria }.distinct().sorted()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    // productos filtrados

    val productosFiltrados: StateFlow<List<ProductoDto>> =
        combine(
            productos,
            nombreFiltro,
            categoriaFiltro,
            precioMin,
            precioMax
        ) { lista, nombre, categoria, min, max ->

            lista.filter { producto ->
                val coincideNombre = nombre.isBlank() ||
                        producto.nombre.contains(nombre, ignoreCase = true)

                val coincideCategoria = categoria.isBlank() ||
                        producto.categoria.equals(categoria, ignoreCase = true)

                val coincideMin = min == null || producto.precio >= min

                val coincideMax = max == null || producto.precio <= max

                coincideNombre && coincideCategoria && coincideMin && coincideMax
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // contador de filtros activos
    val filtrosActivos: StateFlow<Int> = combine(
        nombreFiltro,
        categoriaFiltro,
        precioMin,
        precioMax

    ) { nombre, categoria, min, max ->
        var count = 0
        if (nombre.isNotBlank()) count++
        if (categoria.isNotBlank()) count++
        if (min != null) count++
        if (max != null) count++
        count

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )
}