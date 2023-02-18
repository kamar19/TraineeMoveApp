package com.example.traineemoveapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
        var id: Int? = null,
        var name: String = "",
        var id_photo: Int = -1,
        var date_publication: String = "",
        var rating: Int = 0,
        var description: String = ""
)

