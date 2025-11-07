package com.example.agroverdaspados.data.remote.dto

// respuesta del endpoint de api
data class WeatherResponse(

    // nombre ciudad
    val name: String,

    // temperaturta y humedad
    val main: MainInfo,

    // lista con informacion del clima
    val weather: List<WeatherInfo>
)

data class MainInfo(

    // temperatura actual
    val temp: Double,

    // Humedad
    val humidity: Int
)

// elementos de la lista weatherAPI
data class WeatherInfo(

    // breve descripcion
    val description: String
)
