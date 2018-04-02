package com.example.matthias.postfitty.Api;

import com.example.matthias.postfitty.Model.Post;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostFittyService {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @POST("/posts/")
    Call<Void> createNewPost(@Body JsonObject dataJson);
}
