package com.example.traineemoveapp.data.remote

import com.example.traineemoveapp.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DTO {

    @Serializable
    data class ResultMovie(
            @SerialName("page")
            val page: Int,
            @SerialName("results")
            val movieForNETS: List<MovieForNET>
    )

    @Serializable
    data class MovieForNET( // класс для работы с некоторыми запросами API
            @SerialName("id")
            val id: Long,
            @SerialName("backdrop_path")
            val backdropPicture: String,
            @SerialName("title")
            val title: String,
            @SerialName("poster_path")
            val posterPicture: String,
            @SerialName("genre_ids")
            val genreIds: List<Int>,
            @SerialName("vote_average")
            val vote_average: Float,
            val overview: String,
            val adult: Boolean,
            val vote_count: Int
    )



    @Serializable
    data class MovieDetail( // класс для получения запросов Detail из API
            @SerialName("id")
            val id: Long,
            @SerialName("backdrop_path")
            val backdropPicture: String,
            @SerialName("title")
            val title: String,
            @SerialName("release_date")
            val release_date: String,
            @SerialName("poster_path")
            val posterPicture: String,
            @SerialName("genres")
            val genreIds: List<Genre>,
            @SerialName("vote_average")
            val vote_average: Float,
            val overview: String,
            val adult: Boolean,
            val vote_count: Int
    )

}