package com.example.weatherapp

data class WeatherJson (
    var name: String,
    var main: MainJson,
    var weather: Array<WeatherJson>
)

data class MainJson (
    var  temp: Double,
    var pressure: String,
    var hymidiy: String,
    var icon: String

)