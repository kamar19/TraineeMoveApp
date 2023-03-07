package com.example.traineemoveapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Actor(
        @SerialName("id")
        var actorId: Int = 0,
        var actorMovieId: Long = 0,
        @SerialName("profile_path")
        var picture: String? = "",
        @SerialName("name")
        var actorName: String = ""
) : Parcelable

