package com.example.traineemoveapp.viewModel

import androidx.compose.compiler.plugins.kotlin.lower.forEachWith
import androidx.lifecycle.ViewModel
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.FilmRepository
import kotlinx.coroutines.flow.*

class MainActivityViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private val genres = initGenres()

    private val _uiState = MutableStateFlow(ViewModelListState())
    val uiState: StateFlow<ViewModelListState> get() = _uiState.asStateFlow()

    data class ViewModelListState(val films: MutableList<Film> = mutableListOf())

    init {
        startValue()

    }

    fun startValue() {
        _uiState.value = ViewModelListState(films = filmRepository.getAllFils())
    }

    fun getImage(idImage: Int): Int {
        when (idImage) {
            1 -> return R.drawable.image1
            2 -> return R.drawable.image2
            3 -> return R.drawable.image3
            4 -> return R.drawable.image4
            else -> return R.drawable.image1
        }
    }
    fun getAllGenres(): MutableList<Genre> {
        return genres
    }

    fun getGenre(idGenres: Int): Genre? {
        return genres.findLast { it.id == idGenres }
    }

    private fun initGenres(): MutableList<Genre> {
        val genres: MutableList<Genre> = arrayListOf()
        genres.add(Genre(1, "боевики"))
        genres.add(Genre(2,"драмы"))
        genres.add(Genre(3,"комедии"))
        genres.add(Genre(4,"артхауз"))
        genres.add(Genre(5,"мелодрамы"))
        genres.add(Genre(6,"драмы"))
        return genres
    }



}