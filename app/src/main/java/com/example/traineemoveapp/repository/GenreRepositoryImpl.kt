package com.example.traineemoveapp.repository

import com.example.traineemoveapp.model.Genre

class GenreRepositoryImpl:GenreRepository {
    private val genres = initGenres()

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
}