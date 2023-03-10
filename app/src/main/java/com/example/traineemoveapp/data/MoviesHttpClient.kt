package com.example.traineemoveapp.data

import com.example.traineemoveapp.MainActivity.Companion.BASE_URL
import com.example.traineemoveapp.data.remote.MoviesApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

interface MoviesHttpClient {
    val moviesApi: MoviesApiService
}

class MoviesHttpClientImpl : MoviesHttpClient {
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(TraineeMoveInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
    override val moviesApi: MoviesApiService = this.retrofit.create(MoviesApiService::class.java)
}
