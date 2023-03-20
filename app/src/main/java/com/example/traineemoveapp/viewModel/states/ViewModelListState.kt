package com.example.traineemoveapp.viewModel.states

import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.utils.Result

sealed class ViewModelListState {
    object Loading : ViewModelListState()
    data class Refreshing (var isRefreshing:Boolean) : ViewModelListState()
    data class Success(val listFilm: List<Film>, val genres: List<Genre> ) : ViewModelListState()
    data class Error(val error: Result.Error<Throwable>) : ViewModelListState()
}