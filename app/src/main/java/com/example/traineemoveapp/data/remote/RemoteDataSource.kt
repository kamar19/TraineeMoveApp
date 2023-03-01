package com.example.traineemoveapp.data.remote

import com.example.traineemoveapp.data.NetworkClient
import kotlinx.coroutines.delay

class RemoteDataSource {

    suspend fun getMovies(seachMovie: String): DTO.ResultMovie {
        return  NetworkClient
            .create().getMovies(seachMovie)
    }





}