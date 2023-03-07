package com.example.traineemoveapp.repository

import android.content.Context
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FromJsonRepository(context: Context, val gson: Gson,) : FilmRepository {
    val stream = context.assets.open(MainActivity.ASSET_FILE_NAME)
    val stream2 = context.assets.open(MainActivity.ASSET_FILE_NAME_ACTOR)
    private var films: MutableList<Film> = initFilms(stream)
    private val genres = initGenres()
    private val actors = initActors(stream2)

    private fun initFilms(stream: java.io.InputStream): MutableList<Film> {
        return loadFilms(stream)
    }

    private fun readAssetFileToString(stream: java.io.InputStream): String {
        return stream.bufferedReader().readText()
    }

    fun loadFilms(stream: java.io.InputStream): MutableList<Film> {
        val data = readAssetFileToString(stream)
        return parseMovies(data)
    }

    fun parseMovies(data: String): MutableList<Film> {
        return gson.fromJson(data, object : TypeToken<MutableList<Film>>() {}.type)
    }

    override fun getAllFils(): MutableList<Film> {
        return films
    }

    override fun getFilm(id: Int): Film? {
        return films.find { it.id == id }
    }

    private fun initGenres(): MutableList<Genre> {
        val genres: MutableList<Genre> = arrayListOf()
        genres.add(Genre(1, "боевики"))
        genres.add(Genre(2, "драмы"))
        genres.add(Genre(3, "комедии"))
        genres.add(Genre(4, "артхауз"))
        genres.add(Genre(5, "мелодрамы"))
        genres.add(Genre(6, "драмы"))
        return genres
    }

    override fun getAllGenre(): MutableList<Genre> {
        return genres
    }

    override fun getGenre(idGenres: Int): Genre? {
        return genres.findLast { it.id == idGenres }

    }

    private fun initActors(stream: java.io.InputStream): MutableList<Actor> {
        return loadActors(stream)
    }

    override fun getAllActor(): MutableList<Actor> {
        return actors
    }

    override fun getActor(idActor: Int): Actor? {
        return actors.findLast { it.id == idActor }

    }

    private fun readAssetActorFileToString(stream: java.io.InputStream): String {
        return stream.bufferedReader().readText()
    }

    fun loadActors(stream: java.io.InputStream): MutableList<Actor> {
        val data = readAssetActorFileToString(stream)
        return parseActors(data)
    }

    fun parseActors(data: String): MutableList<Actor> {
        return gson.fromJson(data, object : TypeToken<MutableList<Actor>>() {}.type)
    }

}