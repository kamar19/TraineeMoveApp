package com.example.traineemoveapp.viewModel

import com.example.traineemoveapp.model.Film

sealed class ViewModelListState {
    object Loading : ViewModelListState()
    data class Success(val listFilm: List<Film>) : ViewModelListState()
    data class Error(val error: String) : ViewModelListState()
}