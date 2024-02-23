package com.example.weatherapp.activities

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.rest_api.JSONParser
import com.example.weatherapp.rest_api.model.UserInfo


class mysql_webserviceActivity : AppCompatActivity() {
    lateinit var  edTexNom: EditText
    lateinit var edTextAge: EditText
    lateinit var edTextEmail: EditText
    lateinit var btnAjouter: Button
    lateinit var  parser: JSONParser

    private val _url = "http://10.0.2.2/htdocs/User/add.php/"
    private val _userCode = "264E92FC-HY57A-5845-875CB-29DEHJUYT3"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mysl_webservce)

        edTexNom = findViewById(R.id.editTextText_Nom)
        edTextAge = findViewById(R.id.editTextNumber_age)
        edTextEmail = findViewById(R.id.editText_email)
        btnAjouter = findViewById(R.id.button_ajouter)


        btnAjouter.setOnClickListener{
           // add()
            //addDummyUser()
            addUser()
        }
    }

    // ___ AJOUT USER _____
    fun addUser (){
        val progressDialog= AlertDialog.Builder(this)
        progressDialog.setMessage("Patientez svp.")
        progressDialog.show()

        var success: Int

       // val apiService = UserDataManager()
        val user = UserInfo(
            userId = null,
            userName = edTexNom.text.toString(),
            userAge = edTextAge.text.length,
            userEmail = edTextEmail.text.toString(),
            code = _userCode
        )

        val hashMap : HashMap<String, Any> = HashMap()

        hashMap["nom"] = user.userName.toString()
        hashMap["age"] = user.userAge.toString()
        hashMap["email"] = user.userEmail.toString()
        hashMap["codeUser"] = user.code


        parser = JSONParser()
        val result = parser.JmakeHttpRequest(_url, "GET", hashMap )

        try {
            success = result!!.getInt("success")

            if (success == 1){
                clearText()
                Toast.makeText(this, "Ajout effectué.", Toast.LENGTH_LONG)
            }else{
                Toast.makeText(this, "Echec!! Ajout non effeectué.", Toast.LENGTH_LONG)
            }
           // progressDialog.
        }catch (ex: Exception){

        }
    }

    fun clearText(){
        if (!edTexNom.text.isEmpty() && !edTextAge.text.isEmpty() && !edTextEmail.text.isEmpty()){
            edTexNom.text.clear()
            edTextAge.text.clear()
            edTextEmail.text.clear()
        }
    }
/*
    fun addDummyUser() {
        val apiService = UserDataManager()
        val userInfo = UserInfo(
            userId = null,
            userName = edTexNom.text.toString(),
            userAge = edTextAge.text.length,
            userEmail = edTextEmail.text.toString(),
            code = "164E92FC-D37A-4946-81CB-29DE7EE4B124"
        )

        apiService.addUser(userInfo){
            val response = it
        }

        apiService.addUser(userInfo) {
            if (it?.userId != null) {
                val toto = it.userName
                // it = newly added user parsed as response
                // it?.id = newly added user ID
            } else {
                //TimeBuilder.("Error registering new user")
            }
        }
    }

    private fun add() {

        val user = UserInfo(
            userId      =   null,
            userName    =   edTexNom.text.toString(),
            userAge     =   edTextAge.text.length,
            userEmail    =  edTextEmail.text.toString(),
            code = "164E92FC-D37A-4946-81CB-29DE7EE4B124"
        )

        // TODO1 : create retrofit instance
        val retrofit = ServiceBuilder
        /*val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2/htdocs/User/add.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        val userService = retrofit.builderService(UserApi::class.java)

        try {
            val result = userService.addUser(user)
            result.enqueue(object : Callback<UserInfo>
            {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val message = response!!.message()
                    var success = response.body()


                    if (response.isSuccessful){
                        val result = response.body()
                        val id = result?.userId
                        val nom = result?.userName
                        val age = result?.userAge


                        /*val main = result?.get("main")?.asJsonObject
                        val temp = main?.get("temp")?.asDouble
                        val cityName = result?.get("name")?.asString

                        val weather = result?.get("weather")?.asJsonArray
                        val icon = weather?.get(0)?.asJsonObject?.get("icon")?.asString
*/

                    }

                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Toast.makeText(applicationContext, "Erreur serveur", Toast.LENGTH_SHORT).show()
                }
            }
            )
        }catch (e: Exception){
            Toast.makeText(applicationContext, "Erreur ", Toast.LENGTH_SHORT)
        }
    }
*/

}
