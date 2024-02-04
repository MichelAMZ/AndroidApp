package com.example.weatherapp

data class WeatherResult (
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

data class WeatherJson(
   var id: Int,
    var main: String,
    var description: String,
    var icon: String
)