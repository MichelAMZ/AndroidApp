package com.example.weatherapp.rest_api.model

import com.example.weatherapp.interfaces.UserApi
import com.example.weatherapp.rest_api.HelperPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataManager {

    fun addUser (userData: UserInfo, onResult: (UserInfo?) -> Unit ){
        val retrofit = HelperPreferences.ServiceBuilder.builderService(UserApi::class.java)

        retrofit.addUser(userData).enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable){
                    onResult(null)
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}