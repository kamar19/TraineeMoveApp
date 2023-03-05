package com.example.traineemoveapp.viewModel

import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre


sealed class ViewModelGenresListState {
    object Loading : ViewModelGenresListState()
    data class Success(val genresList: MutableList<Genre>) : ViewModelGenresListState()
    data class Error(val error: String) : ViewModelGenresListState()
}