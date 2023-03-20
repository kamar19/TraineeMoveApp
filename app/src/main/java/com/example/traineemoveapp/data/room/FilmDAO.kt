package com.example.traineemoveapp.data.room

import androidx.room.*
import com.example.traineemoveapp.model.*

@Dao
interface FilmDAO {
    @Query("SELECT * FROM filmsTable")
    fun getFilms(): List<FilmRelation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFilm(film: FilmEntity)

    suspend fun saveToDAO(films: List<Film>) {
        films.forEach {
            saveFilm(
                FilmEntity(
                    it.id,
                    it.title,
                    it.posterPicture,
                    it.ratings,
                    it.overview,
                    it.adult,
                )
            )
        }
    }

    @Query("SELECT * FROM genreTable")
    fun getAllGenre(): List<GenreEntity>

    @Query("SELECT * FROM genreTable WHERE genreFilmId = :idFilm")
    fun getGenresFromFilm(idFilm: Long): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGenres(genres: List<GenreEntity>)
}