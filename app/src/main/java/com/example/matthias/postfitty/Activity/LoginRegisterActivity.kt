package com.example.matthias.postfitty.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthias.postfitty.Api.Webservice
import com.example.matthias.postfitty.Fragments.LoginFragment
import com.example.matthias.postfitty.Fragments.RegisterFragment
import com.example.matthias.postfitty.R
import com.example.matthias.postfitty.Utils.FragmentType

class LoginRegisterActivity : AppCompatActivity() {

    lateinit var webservice: Webservice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        Webservice.createRetrofit()
        loadLoginFragment()
    }

    fun loadLoginFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.loginRegisterFramelayout, LoginFragment.newInstance())
                .commit()
    }

    fun handleFragment(fragmentType: FragmentType) {
        if (fragmentType == FragmentType.LOGIN) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginRegisterFramelayout, LoginFragment.newInstance())
                    .commit()
        }
        if (fragmentType == FragmentType.REGISTER) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.loginRegisterFramelayout, RegisterFragment.newInstance())
                    .commit()
        }
    }
}
