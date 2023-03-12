package com.example.traineemoveapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.traineemoveapp.data.room.DBContract
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Genre(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID)
        @SerialName("id")
        var genreId: Int = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_MOVIEID)
        var genreFilmId: Long = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_NAME)
        @SerialName("name")
        var name: String = ""
) : Parcelable

@Entity(
    tableName = DBContract.FilmsColumns.TABLE_NAME_GENRE,
    primaryKeys = arrayOf(DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID_JOINT)
)
@Serializable
@Parcelize
data class GenreEntity(
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID_JOINT)
        @SerialName(DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID_JOINT)
        var id_joint: Long,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_ID)
        @SerialName("id")
        var genreId: Int = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_MOVIEID)
        var genreFilmId: Long = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_GENRE_NAME)
        @SerialName("name")
        var name: String = ""
) : Parcelable



