package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.repository.FilmRepository
import kotlinx.coroutines.flow.*

class MainActivityViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ViewModelListState())
    val uiState: StateFlow<ViewModelListState> get() = _uiState.asStateFlow()

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

    data class ViewModelListState(val films: MutableList<Film> = mutableListOf())
}