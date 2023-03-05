package com.example.traineemoveapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Film(
        var id: Long,
        var title: String = "",
        @SerialName("poster_path")
        var posterPicture:  String,
        @SerialName("backdrop_path")
        var backdropPicture: String = "",
//        var runtime: Int,
        @SerialName("vote_average")
        var overview: String = "",
        @SerialName("genre_ids")
        var genres:@RawValue List<Int>,
        var ratings: Float = 0.0F,
        var adult: String,
        var vote_count: Int
//        var actor_ids:List<Int>
//        var genres: List<Genre>,
//        var actors: List<Actor>,

): Parcelable

//@Parcelize
//@Serializable
//data class Movie(
//        var id: Long,
//        var title: String,
//        @SerialName("poster_path")
//        var posterPicture: String,
//        @SerialName("backdrop_path")
//        var backdropPicture: String,
//        var runtime: Int,
//        @SerialName("vote_average")
//        var description: String = "",
//        @SerialName("genre_ids")
//        var genres: @RawValue List<Genre>,
////        var actors: @RawValue List<Actor>,
//        @SerialName("vote_average")
//        var ratings: Float,
//        var overview: String,
//        var adult: Int,
//        var vote_count: Int
//) : Parcelable