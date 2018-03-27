package com.example.matthias.postfitty.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.matthias.postfitty.Api.Webservice
import com.example.matthias.postfitty.Event.OnPostsReceivedEvent
import com.example.matthias.postfitty.Model.Post
import com.example.matthias.postfitty.R
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    val webservice = Webservice()
    var posts = listOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webservice.loadPosts()

        openMapsButton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe()
    fun onEvent(event: OnPostsReceivedEvent) {
        posts = event.posts
    }
}