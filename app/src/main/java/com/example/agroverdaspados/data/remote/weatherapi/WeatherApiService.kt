package com.example.agroverdaspados.data.remote.weatherapi

import com.example.agroverdaspados.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        // api key que me dio weather
        @Query("appid") apiKey: String = "97413f5c744202c514c51d7b858b16b1",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): WeatherResponse
}
