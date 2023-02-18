package com.example.traineemoveapp.repository

import android.content.Context
import com.example.traineemoveapp.MainActivity.Companion.ASSET_FILE_NAME
import com.example.traineemoveapp.model.Film
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FromJsonRepository(context: Context) : FilmRepository {
    var films: MutableList<Film> = arrayListOf<Film>()

    init {
        films = initFilms(context)
    }

    private fun initFilms(context: Context): MutableList<Film> {
        return loadFilms(context, ASSET_FILE_NAME)
    }

    private fun readAssetFileToString(context: Context, fileName: String): String {
        val stream = context.assets.open(fileName)
        return stream.bufferedReader().readText()
    }

    fun loadFilms(context: Context, fileName: String): MutableList<Film> {
        val data = readAssetFileToString(context, fileName)
        return parseMovies(data)
    }

    fun parseMovies(data: String): MutableList<Film> {
        return Gson().fromJson(data, object : TypeToken<MutableList<Film>>() {}.type)
    }

    override fun getAllFils(): MutableList<Film> {
        return films
    }

    override fun getFilm(id: Int): Film? {
        return films.find { it.id == id }
    }
}