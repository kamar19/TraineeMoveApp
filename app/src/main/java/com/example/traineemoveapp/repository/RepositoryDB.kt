package com.example.traineemoveapp.repository

import com.example.traineemoveapp.data.room.FilmDAO
import com.example.traineemoveapp.data.room.TraineeMoveDatabase
import com.example.traineemoveapp.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryDB @Inject constructor(traineeMoveDatabase: TraineeMoveDatabase) {
    val filmDAO: FilmDAO = traineeMoveDatabase.movieDAO

    suspend fun convertFilmRelationToFilm(filmsList: List<FilmRelation>): List<Film> {
        val newMovies: MutableList<Film> = mutableListOf()
        for (filmRelation in filmsList) {
            val movie = Film(
                id = filmRelation.film.id,
                title = filmRelation.film.title,
                posterPicture = filmRelation.film.posterPicture,
                ratings = filmRelation.film.ratings,
                overview = filmRelation.film.overview,
                genres = getGenreIds2(filmRelation.film.id),
                adult = filmRelation.film.adult
            )
            newMovies.add(movie)
        }
        return newMovies
    }

    fun convertGenreEntityToGenre(genresEntity: List<GenreEntity>): List<Genre> {
        val genres: MutableList<Genre> = mutableListOf()
        for (genreEntity in genresEntity) {
            val genre = Genre(genreId = genreEntity.genreId, genreFilmId = genreEntity.genreFilmId, name = genreEntity.name )
            genres.add(genre)
        }
        return genres
    }

    fun convertGenreyToGenreEntity(genres: List<Genre>): List<GenreEntity> {
        val genreEntity: MutableList<GenreEntity> = mutableListOf()
        for (genre in genres) {
            val genre = GenreEntity(id_joint = genre.genreId + genre.genreFilmId, genreId = genre.genreId, genreFilmId = genre.genreFilmId, name = genre.name)
            genreEntity.add(genre)
        }
        return genreEntity
    }


    fun getGenreIds(genreList: List<GenreEntity>):List <Int> {
        val ids: MutableList <Int> = arrayListOf()
        genreList.forEach{ ids.add(it.genreId)}
        return ids
    }

    suspend fun getGenreIds2(idFilm: Long):List <Int> {
        val genres: List <GenreEntity> = getGenreFromDB(idFilm)
        return getGenreIds(genres)
    }

    suspend fun getGenreFromDB(idFilm: Long):List <GenreEntity> =
        withContext(Dispatchers.IO) {
            filmDAO.getGenresFromFilm(idFilm)
//            filmDAO.getAllGenre()
        }


    suspend fun saveFilmsToDB(films: List<Film>) {
        withContext(Dispatchers.IO) {
            filmDAO.saveToDAO(films)
        }
    }

    suspend fun saveGenreToDB(films: List<Film>, genres: List<Genre>) {
        withContext(Dispatchers.IO) {
            val selectedGenres:List<GenreEntity> = selectFilmsGenres(films, genres)
            filmDAO.insertGenres(selectedGenres)
        }
    }

    fun selectFilmsGenres(films: List<Film>, genres: List<Genre>):List<GenreEntity> {
        val resultGenre: MutableList<GenreEntity> = arrayListOf()
        for (genre in genres) {
            films.forEach{
                it.genres.forEach {it2->
                    if (genre.genreId  == it2) {
                          resultGenre.add(GenreEntity(id_joint = it.id + genre.genreId, genreFilmId = it.id, genreId = genre.genreId, name = genre.name))
                        }
                }
            }
        }
        return resultGenre
    }
}
