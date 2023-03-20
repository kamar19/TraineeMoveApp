package com.example.traineemoveapp.data.remote

import android.util.Log
import com.example.traineemoveapp.data.FilmsHttpClientImpl
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.utils.doOnError
import com.example.traineemoveapp.utils.runOperationCatching
import com.example.traineemoveapp.utils.Result

class RemoteDataSource {
    val  filmsHttpClient =  FilmsHttpClientImpl()
    suspend fun getFilms(seachMovie: String): Result<List<DTO.FilmForNET>, Throwable> =
        runOperationCatching { filmsHttpClient.moviesApi.getMovies(seachMovie).movies }
            .doOnError { error -> Log.e("Search movies from server error", error.toString()) }

    suspend fun getGenres(): Result<List<Genre>, Throwable> =
        runOperationCatching { filmsHttpClient.moviesApi.getSearchGenres().genres }
            .doOnError { error -> Log.e("Search genres from server error", error.toString()) }


    suspend fun getActors(movie_id: Long?): Result<List<Actor>, Throwable> =
        runOperationCatching { filmsHttpClient.moviesApi.getSearchActors(movie_id).actors }
            .doOnError { error -> Log.e("Search actors from server error", error.toString()) }

    suspend fun getMovie(movie_id: Long): Result<DTO.FilmDetail, Throwable> =
        runOperationCatching { filmsHttpClient.moviesApi.getMovie(movie_id)}
            .doOnError { error -> Log.e("Search movie from server error", error.toString()) }

}