package com.example.agroverdaspados.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agroverdaspados.viewmodel.HomeViewModel
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.agroverdaspados.R
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit = {},
    onProductosClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    viewModel: HomeViewModel? = null
) {

    val actualViewModel = viewModel ?: viewModel()

    val weather by actualViewModel.weather.collectAsState()
    val loading by actualViewModel.loading.collectAsState()
    val error by actualViewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // titulo
        Text(
            text = "Bienvenido a\nAgroVerdeSPA",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(24.dp))

        Spacer(Modifier.height(12.dp))

        // boton perfil
        Button(
            onClick = onProfileClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mi Perfil")
        }

        Spacer(Modifier.height(12.dp))

        // boton productos
        Button(
            onClick = onProductosClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Productos")
        }

        Spacer(Modifier.height(12.dp))

        // boton clima
        Button(
            onClick = { actualViewModel.loadWeather("Santiago") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Clima")
        }

        Spacer(Modifier.height(16.dp))

        // informacion clima
        when {
            loading -> Text("Cargando clima...")
            error != null -> Text(error ?: "")
            weather != null -> {
                Text("Ciudad: ${weather!!.name}")
                Text("Temp: ${weather!!.main.temp}°C")
                Text("Humedad: ${weather!!.main.humidity}%")
                Text("Descripción: ${weather!!.weather.first().description}")
            }
        }

        Spacer(Modifier.height(24.dp))

        // pequeña descripcion
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Acerca de Nosotros",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Somos una empresa dedicada a la venta y distribución de productos agrícolas orgánicos. Nuestra misión es proveer alimentos saludables y sustentables, conectando a productores locales con consumidores que valoran la calidad.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}


