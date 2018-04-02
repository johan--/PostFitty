package com.example.matthias.postfitty.Api;

import com.example.matthias.postfitty.Event.AbstractWebserviceEvent;
import com.example.matthias.postfitty.Event.OnNewPostCreatedEvent;
import com.example.matthias.postfitty.Event.OnPostsReceivedEvent;
import com.example.matthias.postfitty.Model.Post;
import com.example.matthias.postfitty.Utils.BuildConfig;
import com.example.matthias.postfitty.Utils.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Webservice {

    private PostFittyService postFittyService;
    private final LinkedBlockingQueue<Request> waitingRequests = new LinkedBlockingQueue<>();

    public Webservice() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient = builder.build();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(converterFactory())
                .client(okHttpClient)
                .build();

        postFittyService = restAdapter.create(PostFittyService.class);
    }

    private static GsonConverterFactory converterFactory() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }

    public void loadPosts() {
        new Request<List<Post>>(postFittyService.getPosts(), waitingRequests, this) {
            @Override
            protected AbstractWebserviceEvent newEvent(final List<Post> value, final int code) {
                return new OnPostsReceivedEvent(value, code);
            }
        }.execute();
    }

    public void createPost(final String title, final String message, final double latitude, final double longitude) {
        final JsonObject postData = new JsonObject();
        postData.addProperty("title", title);
        postData.addProperty("message", message);
        postData.addProperty("latitude", latitude);
        postData.addProperty("longitude", longitude);

        new Request<Void>(postFittyService.createNewPost(postData), waitingRequests, this) {
            @Override
            protected AbstractWebserviceEvent newEvent(Void value, int code) {
                return new OnNewPostCreatedEvent(value, code);
            }
        }.execute();
    }
}