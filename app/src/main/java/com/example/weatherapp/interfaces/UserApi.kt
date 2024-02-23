package com.example.weatherapp.interfaces

import com.example.weatherapp.rest_api.model.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApi {
    @Headers("Content-Type: application/json")
    @GET(".")
    fun addUser(@Query("q") userData: UserInfo): Call<UserInfo>
}

