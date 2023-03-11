package com.example.traineemoveapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.traineemoveapp.data.room.DBContract
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = DBContract.FilmsColumns.TABLE_NAME_GENRE,
    foreignKeys = [ForeignKey(
        entity = FilmEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("genreMovieId"),
        onDelete = ForeignKey.CASCADE,
        deferred = true
    )],
    primaryKeys = arrayOf("genreMovieId", "genreId")
)
@Serializable
@Parcelize
data class Genre(
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID)
        @SerialName("id")
        var genreId: Int = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_MOVIEID)
        var genreMovieId: Long = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_NAME)
        @SerialName("name")
        var name: String = ""
) : Parcelable

//
//@Serializable
//data class Genre(var id: Int? = null, var name: String = "")

