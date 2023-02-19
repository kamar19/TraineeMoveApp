package com.example.traineemoveapp.repository

import com.example.traineemoveapp.model.Film

interface FilmRepository {
    fun getAllFils(): MutableList<Film>
    fun getFilm(id: Int): Film?
}