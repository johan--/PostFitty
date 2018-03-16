package com.example.matthias.postfitty.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthias.postfitty.Api.Webservice
import com.example.matthias.postfitty.R

class LoginRegisterActivity : AppCompatActivity() {

    lateinit var webservice: Webservice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        Webservice.createRetrofit()
    }
}
