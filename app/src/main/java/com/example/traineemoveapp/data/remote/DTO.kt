package com.example.traineemoveapp.data.remote

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

}