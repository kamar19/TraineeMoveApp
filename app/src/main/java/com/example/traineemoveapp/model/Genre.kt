package com.example.traineemoveapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(var id: Int? = null,
//                 var genreMovieId: Long = 0,
                 var name: String = "")

@Serializable
data class ResultGenre(
        @SerialName("genres")
        val genres: MutableList<Genre>
)