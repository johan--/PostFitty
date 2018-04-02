package com.example.matthias.postfitty.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthias.postfitty.Fragments.LoginFragment
import com.example.matthias.postfitty.R

class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        loadLoginFragment()
    }

    fun loadLoginFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.loginRegisterFramelayout, LoginFragment.Companion.newInstance())
                .commit()
    }
}
