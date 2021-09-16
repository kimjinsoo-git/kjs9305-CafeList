package com.example.macaronagaintoay.Api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory




class Retrofit {


    private final var BASE_URL="http://kjs9305.gonetis.com:9305/"

    private var retrofit: Retrofit? = null


    fun getClient(): Retrofit? {

        var gson = GsonBuilder()
            .setLenient()
            .create()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }


}