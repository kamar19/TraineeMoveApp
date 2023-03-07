package com.example.traineemoveapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Film(var id: Long,
                var title: String = "",
                @SerialName("poster_path")
                var posterPicture: String,
                @SerialName("backdrop_path")
                var backdropPicture: String = "",
                @SerialName("vote_average")
                var overview: String = "",
                @SerialName("genre_ids")
                var genres: @RawValue List<Int>,
                var ratings: Float = 0.0F,
                var adult: String,
                var vote_count: Int
) : Parcelable