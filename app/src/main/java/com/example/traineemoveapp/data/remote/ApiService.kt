package com.example.traineemoveapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/{seachMovie}?language=ru-ru&query=2&include_adult=false")
    suspend fun getMovies(@Path("seachMovie") seachMovie: String): DTO.ResultMovie

}