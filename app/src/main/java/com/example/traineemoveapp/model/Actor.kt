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
        tableName = DBContract.FilmsColumns.TABLE_NAME_ACTOR,
        foreignKeys = [ForeignKey(
                entity = FilmEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("actorMovieId"),
                onDelete = ForeignKey.CASCADE,
        )],
        primaryKeys = arrayOf("actorMovieId", "actorId")
)
@Serializable
@Parcelize
data class Actor(
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_ACTOR_ID)
        @SerialName("id")
        var actorId: Int = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_ACTOR_MOVIEID)
        var actorMovieId: Long = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_ACTOR_PICTURE)
        @SerialName("profile_path")
        var picture: String? = "",
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_ACTOR_NAME)
        @SerialName("name")
        var actorName: String
) : Parcelable



