package com.example.agroverdaspados.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agroverdaspados.viewmodel.ProductosViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen() {

    val viewModel: ProductosViewModel = viewModel()

    val nombre by viewModel.nombreFiltro.collectAsState()
    val categoria by viewModel.categoriaFiltro.collectAsState()
    val categorias by viewModel.categoriasDisponibles.collectAsState()
    val precioMin by viewModel.precioMin.collectAsState()
    val precioMax by viewModel.precioMax.collectAsState()
    val productosRaw by viewModel.productosFiltrados.collectAsState()

    var ordenPrecioAsc by remember { mutableStateOf<Boolean?>(null) }
    val productos = remember(productosRaw, ordenPrecioAsc) {
        when (ordenPrecioAsc) {
            true -> productosRaw.sortedBy { it.precio }
            false -> productosRaw.sortedByDescending { it.precio }
            else -> productosRaw
        }
    }

    var categoriaExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Productos Disponibles",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        // buscar productos por nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.actualizarNombre(it) },
            label = { Text("Buscar por nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        // categoria
        ExposedDropdownMenuBox(
            expanded = categoriaExpanded,
            onExpandedChange = { categoriaExpanded = it }
        ) {
            OutlinedTextField(
                value = if (categoria.isBlank()) "Todas" else categoria,
                onValueChange = {},
                readOnly = true,
                label = { Text("Categoría") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = categoriaExpanded,
                onDismissRequest = { categoriaExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Todas") },
                    onClick = {
                        viewModel.actualizarCategoria("")
                        categoriaExpanded = false
                    }
                )

                categorias.forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(cat) },
                        onClick = {
                            viewModel.actualizarCategoria(cat)
                            categoriaExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // orden por precio (minimo/maximo)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedTextField(
                value = precioMin?.let { it.toInt().toString() } ?: "",
                onValueChange = { viewModel.actualizarPrecioMin(it) },
                label = { Text("Precio mín") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            OutlinedTextField(
                value = precioMax?.let { it.toInt().toString() } ?: "",
                onValueChange = { viewModel.actualizarPrecioMax(it) },
                label = { Text("Precio máx") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        Spacer(Modifier.height(12.dp))

        // ordenar productos
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Ordenar precio:")

            Row(verticalAlignment = Alignment.CenterVertically) {

                TextButton(onClick = { ordenPrecioAsc = true }) {
                    Icon(Icons.Default.ArrowDropUp, contentDescription = null)
                    Text("Asc")
                }

                TextButton(onClick = { ordenPrecioAsc = false }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    Text("Desc")
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // limpia filtros (borra todo)
        TextButton(
            onClick = {
                ordenPrecioAsc = null
                viewModel.limpiarFiltros()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Limpiar filtros")
        }

        Spacer(Modifier.height(12.dp))

        // lista productos
        if (productos.isEmpty()) {
            Text("No se encontraron productos", color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(productos) { p ->
                    ProductoItemSimple(p)
                }
            }
        }
    }
}

// moneda a CLP

@Composable
fun ProductoItemSimple(producto: com.example.agroverdaspados.data.remote.dto.ProductoDto) {

    val formatoCLP = NumberFormat.getInstance(Locale("es", "CL"))

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(producto.nombre, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("Categoría: ${producto.categoria}")
            Text("Productor: ${producto.productor}")
            Text("Precio: $${formatoCLP.format(producto.precio)}")
        }
    }
}
