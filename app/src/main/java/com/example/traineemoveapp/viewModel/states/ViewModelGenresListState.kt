package com.example.traineemoveapp.viewModel.states

import com.example.traineemoveapp.model.Genre

sealed class ViewModelGenresListState {
    object Loading : ViewModelGenresListState()
    data class Success(val genresList: List<Genre>) : ViewModelGenresListState()
    data class Error(val error: String) : ViewModelGenresListState()
}