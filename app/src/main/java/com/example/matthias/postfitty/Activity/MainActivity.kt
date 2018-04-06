package com.example.matthias.postfitty.Activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.matthias.postfitty.Fragments.MapsFragment
import com.example.matthias.postfitty.Fragments.SettingsFragment
import com.example.matthias.postfitty.Fragments.UserProfileFragment
import com.example.matthias.postfitty.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainFrameLayout = R.id.mainFrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {
                supportFragmentManager.beginTransaction()
                        .replace(mainFrameLayout, MapsFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user_profile -> {
                supportFragmentManager.beginTransaction()
                        .replace(mainFrameLayout, UserProfileFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction()
                        .replace(mainFrameLayout, SettingsFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
