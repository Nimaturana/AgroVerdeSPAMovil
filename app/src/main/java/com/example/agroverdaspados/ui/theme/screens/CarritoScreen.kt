package com.example.agroverdaspados.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agroverdaspados.viewmodel.CarritoViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel
) {

    val carritoItems by carritoViewModel.cartItems.collectAsState()
    val formatoCLP = NumberFormat.getInstance(Locale("es", "CL"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // titulo
        Text(
            text = "Carrito de compras",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // if si el carrito esta vacio (muestra mensaje)
        if (carritoItems.isEmpty()) {

            Text(
                text = "Tu carrito está vacío",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            // lista de productos agregados al carrito
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(carritoItems) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            // nombre producto
                            Text(
                                text = item.producto.nombre,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))


                            //informacion producto
                            Text("Cantidad: ${item.cantidad}")
                            Text(
                                "Precio unitario: $${formatoCLP.format(item.producto.precio)}"
                            )

                            // subtotal producto
                            Text(
                                "Subtotal: $${formatoCLP.format(item.producto.precio * item.cantidad)}",
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            // precio final y total de carrito
            Text(
                text = "Total: $${formatoCLP.format(carritoViewModel.total())}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // boton finalizar compra
            Button(
                onClick = {
                    carritoViewModel.clearCart()
                    navController.navigate("pedido_exito")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar compra")
            }
        }
    }
}
