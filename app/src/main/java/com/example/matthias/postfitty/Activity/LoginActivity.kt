package com.example.matthias.postfitty.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.matthias.postfitty.Api.Webservice
import com.example.matthias.postfitty.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var webservice: Webservice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        InitializeOnClickListeners()

        Webservice.createRetrofit()
    }

    private fun InitializeOnClickListeners() {
        signUpTextView!!.setOnClickListener {
            val registerActivity = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(registerActivity)
        }
        loginButton!!.setOnClickListener {
            val mainActivity = Intent(applicationContext, UserProfileActivity::class.java)
            startActivity(mainActivity)
        }
    }
}
