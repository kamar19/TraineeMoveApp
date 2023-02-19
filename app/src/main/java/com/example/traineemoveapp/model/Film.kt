package com.example.traineemoveapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
        var id: Int? = null,
        var name: String = "",
        var id_photo: Int = -1,
        var date_publication: String = "",
        var rating: Float = 0.0F,
        var description: String = "",
        var pg: String = ""
)

