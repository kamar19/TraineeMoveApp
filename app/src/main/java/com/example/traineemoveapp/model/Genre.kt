package com.example.traineemoveapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.traineemoveapp.data.room.DBContract
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = DBContract.FilmsColumns.TABLE_NAME_GENRE,
    //primaryKeys = arrayOf("genreFilmId", "genreId")
    primaryKeys = arrayOf("genreId")
)
@Serializable
@Parcelize
data class Genre(
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID)
        @SerialName("id")
        var genreId: Int = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_MOVIEID)
        var genreFilmId: Long = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_NAME)
        @SerialName("name")
        var name: String = ""
) : Parcelable

