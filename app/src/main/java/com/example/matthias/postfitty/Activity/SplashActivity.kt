package com.example.matthias.postfitty.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthias.postfitty.Api.Webservice
import com.example.matthias.postfitty.Model.Post
import com.example.matthias.postfitty.R

class SplashActivity : AppCompatActivity() {

    val webservice = Webservice()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadPosts()
    }

    private fun loadPosts() {
        webservice.loadPosts()

        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    companion object {
        var posts = listOf<Post>()
    }
}
