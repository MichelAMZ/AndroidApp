package com.example.weatherapp.rest_api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HelperPreferences {
    object  ServiceBuilder {
        private val client = OkHttpClient.Builder().build()

        var gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/htdocs/User/add.php/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client).build()

        fun<T>builderService(service: Class<T>): T {
            return retrofit.create(service)
        }
    }

}