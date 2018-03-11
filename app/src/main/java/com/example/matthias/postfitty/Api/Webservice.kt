package com.example.matthias.postfitty.Api

import com.example.matthias.postfitty.Utils.UrlUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Webservice {

    companion object {
        fun createRetrofit(): ApiEndpoints {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(UrlUtils.BASE_URL)
                    .build()

            return retrofit.create(ApiEndpoints::class.java)
        }
    }
}