package com.example.agroverdaspados.ui.theme.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agroverdaspados.viewmodel.WeatherViewModel
import androidx.compose.ui.tooling.preview.Preview


    // aqui se define todo lo que tendra homescreen

@Composable
fun HomeScreen(onProfileClick: () -> Unit) {

    val weatherViewModel: WeatherViewModel = viewModel()

    val weather by weatherViewModel.weather.collectAsState()
    val loading by weatherViewModel.loading.collectAsState()
    val error by weatherViewModel.error.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Bienvenido a AgroVerdeSPA", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Button(onClick = onProfileClick) {
            Text("Ir a perfil")
        }

        Spacer(Modifier.height(32.dp))

        Button(onClick = { weatherViewModel.loadWeather("Santiago") }) {
            Text("Obtener clima")
        }

        Spacer(Modifier.height(16.dp))

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

    }
}
// emulacion sin api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(onProfileClick = {})
    }
}