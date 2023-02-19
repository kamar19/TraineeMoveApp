package com.example.traineemoveapp.repository

import android.content.Context
import com.example.traineemoveapp.MainActivity.Companion.ASSET_FILE_NAME
import com.example.traineemoveapp.model.Film
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FromJsonRepository(stream: java.io.InputStream, val gson: Gson,) : FilmRepository {
    var films: MutableList<Film> = arrayListOf<Film>()

    init {
        films = initFilms(stream)
    }

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
}