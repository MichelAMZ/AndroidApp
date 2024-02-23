package com.example.weatherapp.interfaces

import com.example.weatherapp.rest_api.model.WeatherResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object{
        const val APIKEY = "2a17e5fea9e6c8669d46d35368112499"
    }
    @GET("?units=metric&appid=$APIKEY")
    fun getWeatherByCity(@Query("q") city: String): Call<WeatherResult>

}