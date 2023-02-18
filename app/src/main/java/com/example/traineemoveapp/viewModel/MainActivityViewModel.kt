package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.repository.FilmRepository
import kotlinx.coroutines.flow.*

class MainActivityViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    var films: MutableList<Film> = filmRepository.getAllFils()

    @JvmName("getFilms1") fun getFilms(): MutableList<Film> {
        return films
    }

    fun getImage(idImage:Int):Int {
        when (idImage) {
            1-> return R.drawable.image1
            2-> return R.drawable.image2
            3-> return R.drawable.image3
            4-> return R.drawable.image4
            else -> return R.drawable.image1
        }
    }
}