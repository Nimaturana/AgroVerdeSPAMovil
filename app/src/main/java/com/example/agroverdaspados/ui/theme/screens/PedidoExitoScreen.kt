package com.example.agroverdaspados.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PedidoExitoScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // icono de ticket verde (feedback visual)
        Icon(
            imageVector = Icons.Default.ReceiptLong,
            contentDescription = null,
            tint = Color(0xFF2E7D32), // verde éxito
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        // titulo principal
        Text(
            text = "Compra realizada con éxito",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))


        // mensaje compra exitosa
        Text(
            text = "Tu pedido fue procesado correctamente.\n" +
                    "\n" +
                    "Gracias por preferir comprar en AgroVerdeSPA.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))


        // boton volver al inicio (screen home )

        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Volver al inicio")
        }
    }
}
