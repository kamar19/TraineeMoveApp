package com.example.traineemoveapp.model

import android.os.Parcelable
import androidx.room.*
import com.example.traineemoveapp.data.room.DBContract
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
                var genres: List<Int>,
//                var actors: List<Actor>,
                var ratings: Float = 0.0F,
                var adult: String,
                var vote_count: Int
) : Parcelable

@Entity(
    tableName = DBContract.FilmsColumns.TABLE_NAME,
    indices = [Index(DBContract.FilmsColumns.COLUMN_NAME_ID)],
)
public data class FilmEntity(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_ID)
        var id: Long = 0,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_TITLE)
        var title: String = "",
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_POSTERPICTURE)
        var posterPicture: String = "",
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_BACKDROPPICTURE)
        var backdropPicture: String = "",
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_RUNTIME)
        var ratings: Float = 0F,
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_OVERVIEW)
        var overview: String = "",
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_ADULT)
        var adult: String = "",
        @ColumnInfo(name = DBContract.FilmsColumns.COLUMN_NAME_VOTE_COUNT)
        var vote_count: Int = 0,
//        var genres: List<Int>,
)


data class FilmRelation(
        @Embedded
        val film: FilmEntity,
        @Relation(
            parentColumn = DBContract.FilmsColumns.COLUMN_NAME_ID,
            entityColumn = DBContract.FilmsColumns.COLUMN_NAME_GENRE_MOVIEID,
            entity = Genre::class
        )
        val genreList: List<Genre>,
//        @Relation(
//            parentColumn = DBContract.FilmsColumns.COLUMN_NAME_ID,
//            entityColumn = DBContract.FilmsColumns.COLUMN_NAME_ACTOR_MOVIEID,
//            entity = Actor::class
//        )
//        val actorList: List<Actor>

) {

    fun getGenreIds():List <Int> {
        val ids: MutableList <Int> = arrayListOf()
        genreList.forEach{ ids.add(it.genreId)}
        return ids
    }
}