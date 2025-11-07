package com.example.agroverdaspados.repository


import com.example.agroverdaspados.data.remote.weatherapi.WeatherApiService

class WeatherRepository(private val api: WeatherApiService) {

    // recibe el nombre de la ciudad definida (santiago en mi caso) y retorna la respuesta
    suspend fun getWeather(city: String) =
        api.getWeather(city)
}
