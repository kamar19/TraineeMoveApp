package com.example.traineemoveapp.repository

import com.example.traineemoveapp.MainActivity.Companion.BASE_URL_MOVIES
import com.example.traineemoveapp.data.remote.DTO
import com.example.traineemoveapp.data.remote.RemoteDataSource
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.utils.mapSuccess
import com.example.traineemoveapp.utils.Result
import javax.inject.Inject

class RemoteRepository @Inject constructor(val remoteDataSource: RemoteDataSource) {

    suspend fun loadGenreFromNET(): Result<List<Genre>, Throwable> = remoteDataSource
        .getGenres()
        .mapSuccess { dto ->
            dto.map { genre ->
                Genre(
                    //id_joint = genre.id_joint,
                    genreId = genre.genreId,
                    name = genre.name
                )
            }
        }

    suspend fun loadActorFromNET(idMovie: Long): Result<List<Actor>, Throwable> = remoteDataSource
        .getActors(idMovie)
        .mapSuccess { dto ->
            dto.map { actors ->
                Actor(
                    actorId = actors.actorId,
                    actorfilmId = actors.actorfilmId,
                    picture = BASE_URL_MOVIES.plus(actors.picture),
                    actorName = actors.actorName
                )
            }
        }

    suspend fun loadMovieFromNET(filmId: Long): Result<DTO.FilmDetail, Throwable> = remoteDataSource.getMovie(filmId)

    suspend fun loadMoviesFromNET(seachMovie: String): Result<List<Film>, Throwable> = remoteDataSource
        .getFilms(seachMovie)
        .mapSuccess { dto ->
            dto.map { seachMovie ->
                Film(
                    id = seachMovie.id,
                    title = seachMovie.title,
                    posterPicture = BASE_URL_MOVIES + seachMovie.posterPicture,
                    ratings = seachMovie.vote_average / 2,
                    overview = seachMovie.overview,
                    genres = seachMovie.genreIds,
                    adult = if (seachMovie.adult) "16+" else "13+"
                )
            }
        }
}
