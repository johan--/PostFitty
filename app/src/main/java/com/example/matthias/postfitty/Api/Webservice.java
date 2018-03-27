package com.example.matthias.postfitty.Api;

import com.example.matthias.postfitty.Event.AbstractWebserviceEvent;
import com.example.matthias.postfitty.Event.OnPostsReceivedEvent;
import com.example.matthias.postfitty.Model.Post;
import com.example.matthias.postfitty.Utils.BuildConfig;
import com.example.matthias.postfitty.Utils.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public Webservice(String string) {
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
}
