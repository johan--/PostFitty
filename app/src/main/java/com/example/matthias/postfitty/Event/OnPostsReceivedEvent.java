package com.example.matthias.postfitty.Event;

import com.example.matthias.postfitty.Model.Post;

import java.util.List;

public class OnPostsReceivedEvent extends AbstractWebserviceEvent<List<Post>> {

    private List<Post> posts;

    public OnPostsReceivedEvent(List<Post> value, int httpCode) {
        super(value, httpCode);
        this.posts = value;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
