package com.example.matthias.postfitty.Api;

import com.example.matthias.postfitty.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoints {

    @GET("/posts")
    Call<List<Post>> getPosts();
}