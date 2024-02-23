package com.example.weatherapp.rest_api.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") val userId: Int?,
    @SerializedName("Name") val userName: String,
    @SerializedName("Age") val userAge: Int,
    @SerializedName("email") val userEmail: String,
    @SerializedName("code") val code: String

)