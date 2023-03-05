package com.example.traineemoveapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Actor (var id: Int = 0,
                  var name: String = "",
//                  var actorMovieId: Long = 0,
                  var profile_path:String = "")

@Serializable
data class ResultActor(
        @SerialName("id")
        val page: Int,
        @SerialName("cast")
        val actors: List<Actor>
)