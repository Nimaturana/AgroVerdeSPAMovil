package com.example.agroverdaspados.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.remote.weatherapi.WeatherRetrofitClient
import com.example.agroverdaspados.repository.WeatherRepository
import com.example.agroverdaspados.data.remote.dto.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    // repositorio que contiene la logica de la api
    private val repository = WeatherRepository(WeatherRetrofitClient.api)

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather = _weather.asStateFlow()

    // mensaje de cargando en caso de demorarse
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // mensaje de error al cargar la api clima
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    // funcion para llamar api y obtener clima de cierta ciudad (santiago en mi caso)
    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val response = repository.getWeather(city)
                _weather.value = response
            } catch (e: Exception) {
                _error.value = "No se pudo obtener el clima"
            } finally {
                _loading.value = false
            }
        }
    }
}
