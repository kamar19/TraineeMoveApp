package com.example.traineemoveapp.repository

import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre

interface GenreRepository {
    fun getAllGenre(): MutableList<Genre>
    fun getGenre(id: Int): Genre?
}