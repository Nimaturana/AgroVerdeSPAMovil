package com.example.agroverdaspados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.remote.dto.WeatherResponse
import com.example.agroverdaspados.data.remote.weatherapi.WeatherRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather = _weather.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadWeather(city: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                // Usa WeatherRetrofitClient.api que tiene tu WeatherApiService
                val response = WeatherRetrofitClient.api.getWeather(
                    city = city,
                    units = "metric",
                    lang = "es"
                )
                _weather.value = response
            } catch (e: Exception) {
                _error.value = "Error al cargar el clima: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}