package com.example.agroverdaspados.data.remote.weatherapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitClient {

// api clima en base url(se agrega la api key que dan)
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}
