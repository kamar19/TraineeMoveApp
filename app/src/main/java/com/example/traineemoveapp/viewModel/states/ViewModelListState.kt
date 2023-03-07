package com.example.traineemoveapp.viewModel.states

import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.utils.Result

sealed class ViewModelListState {
    object Loading : ViewModelListState()
    data class Success(val listFilm: List<Film>) : ViewModelListState()
    data class Error(val error: Result.Error<Throwable>) : ViewModelListState()
}