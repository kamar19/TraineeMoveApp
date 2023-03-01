package com.example.traineemoveapp.data

import com.example.traineemoveapp.MainActivity.Companion.BASE_URL
import com.example.traineemoveapp.data.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    fun create(): ApiService =
        Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .addInterceptor(Interceptor()).build())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build().create(ApiService::class.java)
}
