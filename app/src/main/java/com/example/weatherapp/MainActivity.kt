package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.activities.mysql_webserviceActivity
import com.example.weatherapp.email.SendMail
import com.example.weatherapp.rest_api.model.WeatherResult
import com.example.weatherapp.interfaces.WeatherApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var editCityText : EditText

    lateinit var imageWeather: ImageView

    lateinit var tvTemperature: TextView
    lateinit var tvCityName: TextView

    lateinit var layoutWeather: LinearLayout

    lateinit var btnSearch : Button
    lateinit var btnSendMail : Button
    lateinit var btnSendUserWS : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editCityText = findViewById(R.id.editTextTextCity)
        btnSearch = findViewById(R.id.buttonSearch)
        btnSendMail = findViewById(R.id.button_send_mail)
        btnSendUserWS =  findViewById(R.id.button_user_webservice)
        imageWeather = findViewById(R.id.imageViewWeather)
        tvTemperature = findViewById(R.id.textView_Degre)
        tvCityName = findViewById(R.id.textViewCity)
        layoutWeather = findViewById(R.id.linearLayoutWeather)

        val city = "Toulouse"
        getWeatherByCity(city)

        btnSearch.setOnClickListener{
            var city = editCityText.text.toString()

            if (city.isEmpty()){
                Toast.makeText(this, "City's nam can't be empty !", Toast.LENGTH_SHORT).show()
            }else{
                getWeatherByCity(city)
            }
        }

       // btnSendMail.setOnClickListener{
           // Toast.makeText(applicationContext, "Test Mail button", Toast.LENGTH_SHORT)
       // }

        btnSendMail.setOnClickListener( {
           startActivity(Intent(this@MainActivity, SendMail::class.java))
        })

        btnSendUserWS.setOnClickListener( {
            startActivity(Intent(this@MainActivity, mysql_webserviceActivity::class.java))
        })
    }

    private fun getWeatherByCity(cityName: String) {

        // TODO1 : create retrofit instance
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherApi::class.java)
        try {
            // TODO2: call weather api
            val result = weatherService.getWeatherByCity(cityName)
            result.enqueue(object : Callback<WeatherResult>{
                override fun onResponse(call: Call<WeatherResult>, response: Response<WeatherResult>) {
                    if (response.isSuccessful){
                        val result = response.body()

                        /*val main = result?.get("main")?.asJsonObject
                        val temp = main?.get("temp")?.asDouble
                        val cityName = result?.get("name")?.asString

                        val weather = result?.get("weather")?.asJsonArray
                        val icon = weather?.get(0)?.asJsonObject?.get("icon")?.asString
*/
                        Picasso.get().load("https://openweathermap.org/img/w/${result?.weather?.get(0)?.icon}.png").into(imageWeather)

                        tvTemperature.text = "${result?.main?.temp} °C"
                        tvCityName.text = result?.name

                        layoutWeather.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    Toast.makeText(applicationContext, "Erreur serveur", Toast.LENGTH_SHORT).show()
                }

            })
        }catch (ex: Exception){
            Toast.makeText(applicationContext, "Erreur ", Toast.LENGTH_SHORT)
        }
    }
}