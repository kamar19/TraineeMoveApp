package com.example.traineemoveapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {
    @GET("movie/{seachMovie}?language=ru-ru&query=2&include_adult=false")
    suspend fun getMovies(@Path("seachMovie") seachMovie: String): DTO.ResultMovie

    @GET("genre/movie/list?&language=ru-ru")
    suspend fun getSearchGenres(): DTO.ResultGenre

    @GET("movie/{movie_id}/credits?&language=ru-ru")
    suspend fun getSearchActors(@Path("movie_id") movie_id: Long?): DTO.ResultActor

    @GET("movie/{movie_id}?language=ru-ru&query=2&include_adult=false")
    suspend fun getMovie(@Path("movie_id") movie_id: Long?): DTO.FilmDetail
}