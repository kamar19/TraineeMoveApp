package com.example.traineemoveapp.repository

import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre

interface FilmRepository {
    fun getAllFils(): MutableList<Film>
    fun getFilm(id: Int): Film?
    fun getAllGenre(): MutableList<Genre>
    fun getGenre(id: Int): Genre?
    fun getAllActor(): MutableList<Actor>
    fun getActor(id: Int): Actor?
}