package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.FilmRepository
import com.example.traineemoveapp.repository.GenreRepository

class DetailFilmViewModel (val filmRepository: FilmRepository, val genreRepository: GenreRepository, idFilm:Int ) : ViewModel() {
    var film: Film? = filmRepository.getFilm(idFilm)

    fun getImage(idFilm:Int):Int {
        val idImage =  filmRepository.getFilm(idFilm)?.id_photo
        when (idImage) {
            1-> return R.drawable.image1
            2-> return R.drawable.image2
            3-> return R.drawable.image3
            4-> return R.drawable.image4
            else -> return R.drawable.image1
        }
    }

    fun getAllGenres(): MutableList<Genre> {
        return genreRepository.getAllGenre()
    }

    fun getFilmGenres(filmGenres: MutableList<Int>): MutableList<Genre> {
        val allGenres = genreRepository.getAllGenre()
        return allGenres.filter { filmGenres.contains(it.id) } as MutableList<Genre>
    }

}