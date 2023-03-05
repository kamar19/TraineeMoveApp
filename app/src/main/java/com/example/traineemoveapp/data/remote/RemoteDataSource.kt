package com.example.traineemoveapp.data.remote

import android.util.Log
import com.example.traineemoveapp.data.MoviesHttpClientImpl
import com.example.traineemoveapp.model.ResultActor
import com.example.traineemoveapp.model.ResultGenre

class RemoteDataSource {

    val  moviesHttpClient =  MoviesHttpClientImpl()

    suspend fun getMovies(seachMovie: String): DTO.ResultMovie {
        Log.v("test_log","getMovies 01")

        val movies =  moviesHttpClient.moviesApi.getMovies(seachMovie)
        Log.v("test_log","getMovies 02 - movies.movieForNETS.size =  " + movies.movieForNETS.size)

        return  movies
    }

    suspend fun getGenreFromNet(): ResultGenre {
        return moviesHttpClient.moviesApi.getSearchGenre()
    }

    suspend fun getSearchActor(movie_id: Long?): ResultActor {
        return  moviesHttpClient.moviesApi.getSearchActor(movie_id)
    }

    suspend fun getMovie(movie_id: Long): DTO.MovieDetail {
        val movie =  moviesHttpClient.moviesApi.getMovie(movie_id)
        Log.v("test_log","getMovie - movie.title = " + movie.title)
        return  movie
    }


}