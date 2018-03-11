package com.example.matthias.postfitty.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.matthias.postfitty.R

class RegisterActivity : AppCompatActivity() {

    private var loginTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        InitializeViewElements()
        InitializeOnClickListeners()

    }

    private fun InitializeViewElements() {
        loginTextView = findViewById(R.id.LoginTextView) as TextView
    }

    private fun InitializeOnClickListeners() {
        loginTextView!!.setOnClickListener {
            val loginActivity = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginActivity)
        }
    }
}
