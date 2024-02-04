package com.example.weatherapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {

    @GET("?q=Toulouse&appid=2a17e5fea9e6c8669d46d35368112499")
    fun getWeatherByCity(): Call<JsonObject>

}