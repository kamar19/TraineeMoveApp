package com.example.traineemoveapp.repository

import android.util.Log
import com.example.traineemoveapp.data.room.FilmDAO
import com.example.traineemoveapp.data.room.TraineeMoveDatabase
import com.example.traineemoveapp.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryDB(val traineeMoveDatabase: TraineeMoveDatabase) {
    val filmDAO: FilmDAO = traineeMoveDatabase.movieDAO

    fun convertFilmRelationToFilm(filmsList: List<FilmRelation>, genres:List<Genre>): List<Film> {
        val newMovies: MutableList<Film> = mutableListOf()
        for (filmRelation in filmsList) {
//            val newGenres: List<Genre> = selectFilmsGenres(filmsList, genres )
            val movie: Film = Film(
                id = filmRelation.film.id,
                title = filmRelation.film.title,
                posterPicture = filmRelation.film.posterPicture,
                backdropPicture = filmRelation.film.backdropPicture,
                ratings = filmRelation.film.ratings,
                overview = filmRelation.film.overview,
                adult = filmRelation.film.adult,
                vote_count = filmRelation.film.vote_count,
                genres = getGenreIds(filmRelation.genreList),
//                actors = filmRelation.actorList
            )
            newMovies.add(movie)
        }
        return newMovies
    }

        fun getGenreIds(genreList: List<Genre>):List <Int> {
        val ids: MutableList <Int> = arrayListOf()
        genreList.forEach{ ids.add(it.genreId)}
        return ids
    }


    suspend fun saveFilmsToDB(films: List<Film>, genres: List<Genre>) {
        withContext(Dispatchers.IO) {
            val selectedGenres:List<Genre> = selectFilmsGenres(films, genres)
            filmDAO.saveToDAO(films, selectedGenres)
        }
    }

    fun selectFilmsGenres(films: List<Film>, genres: List<Genre>):List<Genre> {
        val resultGenre: MutableList<Genre> = arrayListOf()
        Log.v("test_log", "saveFilms - genres.size = " + genres.size)
        var i = 0
        for (genre in genres) {
            films.forEach{
//                it.genreList.forEach {it2->
                it.genres.forEach {it2->
//                    if (genre == it2) {
                    if (genre.genreId  == it2) {

                        Log.v("test_log", "i = " + i + " genre.name = " + genre.name)
                        i++
                        resultGenre.add(genre)
                    }
                }
            }
        }
        resultGenre.forEach {
            Log.v("test_log", "resultGenre = " + it.name + ", it.genreId = " + it.genreId)
        }
        return resultGenre
    }
}
