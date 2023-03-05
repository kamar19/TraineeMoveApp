package com.example.traineemoveapp.repository

import android.util.Log
import com.example.traineemoveapp.MainActivity.Companion.BASE_URL_MOVIES
import com.example.traineemoveapp.data.remote.DTO
import com.example.traineemoveapp.data.remote.RemoteDataSource
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepository (val remoteDataSource: RemoteDataSource) {
    val allActors: MutableList<Actor> = arrayListOf()
    val allGenres: MutableList<Genre> = arrayListOf()

    suspend fun loadGenreFromNET(idMovie: Long): MutableList<Genre> {
        val genres: MutableList<Genre> = remoteDataSource.getGenreFromNet().genres
        return genres
    }

    suspend fun loadActorFromNET(idMovie: Long): List<Int> {
        val actors:  List<Actor>
        val listIdActors:   MutableList<Int>  = arrayListOf()
        withContext(Dispatchers.IO) {
            actors = remoteDataSource.getSearchActor(idMovie).actors.filter { it.profile_path != null }
            actors.forEach()
            {
                if (it.profile_path != null) {
                    it.profile_path = BASE_URL_MOVIES.plus(it.profile_path)
                    allActors.add(it)
                    listIdActors.add(it.id)
                }
//                it.actorMovieId = idMovie
            }
        }
        // Актеров сохранить в общий список, а id передать в фильм

        return listIdActors
    }

    suspend fun loadMoviesFromNET(seachMovie: String): List<Film> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getMovies(seachMovie).movieForNETS.map { movie2 ->
//                val genres: List<Genre> = loadGenreFromNET(movie2.id)
//                val genresMap = genres.associateBy { it.idGenre .genreId }
                Film(
                    id = movie2.id,
                    title = movie2.title,
                    posterPicture = BASE_URL_MOVIES + movie2.posterPicture,
                    backdropPicture = BASE_URL_MOVIES + movie2.backdropPicture,
//                    runtime = 5, //loadRuntimesFromNET(movie2.id),
                    ratings = movie2.vote_average/2,
                    overview = movie2.overview,
                    vote_count = movie2.vote_count,
                    genres = movie2.genreIds ,
//                    movie2.genreIds.map {
//                        genresMap[it] ?: throw IllegalArgumentException("Genre not found")
//                    },
//                    actors = loadActorFromNET(movie2.id),
                    adult = if (movie2.adult) "16+" else "13+",
                )
            }
        }

    suspend fun loadMovieFromNET(filmId: Long): DTO.MovieDetail =
        withContext(Dispatchers.IO) {
            Log.v("test_log","loadMovieFromNE - filmId = " + filmId.toString())
            remoteDataSource.getMovie(filmId)
            }

}
