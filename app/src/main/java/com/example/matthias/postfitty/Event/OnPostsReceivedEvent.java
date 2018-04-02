package com.example.matthias.postfitty.Event;

import com.example.matthias.postfitty.Activity.SplashActivity;
import com.example.matthias.postfitty.Model.Post;

import java.util.List;

public class OnPostsReceivedEvent extends AbstractWebserviceEvent<List<Post>> {

    public OnPostsReceivedEvent(List<Post> value, int httpCode) {
        super(value, httpCode);
        SplashActivity.Companion.setPosts(value);
    }
}
