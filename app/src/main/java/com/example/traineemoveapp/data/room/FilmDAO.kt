package com.example.traineemoveapp.data.room

import androidx.room.*
import com.example.traineemoveapp.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDAO {
    @Query("SELECT * FROM filmsTable")
    fun getFilms(): List<FilmRelation>

//    @Transaction
//    @Query("SELECT * FROM filmsTable  WHERE id= :idFilm")
//    suspend fun getFilm(idFilm: Long): FilmRelation

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFilm(film: FilmEntity)

    suspend fun saveToDAO(films: List<Film>, genres: List<Genre>) {
        films.forEach {
            saveFilm(FilmEntity(
                it.id,
                it.title,
                it.posterPicture,
                it.backdropPicture,
                it.ratings,
                it.overview,
                it.adult,
                it.vote_count
            ))
            upsertGenres(it.id, genres)
//            saveActors(it. .actors)
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveActors(actors: List<Actor>)

//    @Transaction
//    fun upsertGenres(idFilm: Long, genres: List<Genre>) {
//        genres.forEach{ it.genreFilmId = idFilm }
//        val rowId = insertGenres(genres)
//        val genresToUpdate = rowId.mapIndexedNotNull { index, rowId ->
//            if (rowId == -1L) null else genres[index]
//        }
//        genresToUpdate.forEach { insertGenre(it) }
//    }

    @Transaction
    fun upsertGenres(idFilm: Long, genres: List<Genre>) {
//        genres.forEach{ it.genreFilmId = idFilm }
//        val rowId = insertGenres(genres)
//        val genresToUpdate = rowId.mapIndexedNotNull { index, rowId ->
//            if (rowId == -1L) null else genres[index]
//        }
//        genresToUpdate.forEach { insertGenre(it) }
        insertGenres(genres)
    }


    @Query("SELECT * FROM actorTable WHERE actorId= :idActors")
    fun getAllActor(idActors: List<Int>): List<Actor>

    @Query("SELECT * FROM actorTable WHERE actorId= :id")
    fun getActor(id: Int): Actor

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertActor(actor: Actor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertActors( actors: List<Actor>)

//    @Query("SELECT * FROM genreTable WHERE genreFilmId= :idFilm ")
//    fun getGenresFromIdFilm(idFilm : Int): List<Genre>

//    @Query("SELECT * FROM genreTable WHERE genreId= :id")
//    fun getGenre(id: Int): Genre

    @Query("SELECT * FROM genreTable")
    fun getAllGenre(): List<Genre>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGenres(genres: List<Genre>): List<Long>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertGenre(genre: Genre)
}