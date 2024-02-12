package com.example.weatherapp.email

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R


class SendMail : AppCompatActivity() {
    lateinit var editTextEmail: EditText
    lateinit var editTextSubject: EditText
    lateinit var editTextMessage: EditText
    lateinit var buttonSend: Button
    lateinit var btnCancel: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_mail)

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSubject =  findViewById(R.id.editTextSubject);
        editTextMessage =  findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        btnCancel = findViewById(R.id.buttonCancel)


        buttonSend.setOnClickListener{sendEmail()};


        btnCancel.setOnClickListener {
            startActivity(Intent(this@SendMail, MainActivity::class.java))
        }
    }

    private fun sendEmail() {
        val email = editTextEmail.text.toString().trim { it <= ' ' }
        val subject = editTextSubject.text.toString().trim { it <= ' ' }
        val message = editTextMessage.text.toString().trim { it <= ' ' }
        //val sm = SendMail(email, subject, message)

    }

}