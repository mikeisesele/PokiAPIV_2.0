package com.decagon.pokiapi_v20.utils

import com.decagon.pokiapi_v20.MainActivity.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    // the retrofit method
    private fun getUploadApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    var retroAPIservice: HttpRequests = getUploadApi().create(HttpRequests::class.java)

}