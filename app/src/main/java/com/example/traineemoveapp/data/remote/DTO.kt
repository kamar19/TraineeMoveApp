package com.example.traineemoveapp.data.remote

import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class DTO {
    @Serializable
    data class ResultMovie(
            @SerialName("page")
            val page: Int,
            @SerialName("results")
            val movies: List<FilmForNET>
    )
    @Serializable
    data class ResultGenre(
            @SerialName("genres")
            val genres: List<Genre>
    )
    @Serializable
    data class ResultActor(
            @SerialName("id")
            val page: Int,
            @SerialName("cast")
            val actors: List<Actor>
    )
    @Serializable
    data class FilmForNET(
            @SerialName("id")
            val id: Long,
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
    )
    @Serializable
    data class FilmDetail(
            @SerialName("id")
            val id: Long,
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
    )
}