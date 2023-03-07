package com.example.traineemoveapp.viewModel.states

import com.example.traineemoveapp.data.remote.DTO

open class ViewModelDetailsState {
    object Loading : ViewModelDetailsState()
    data class Success(val film: DTO.MovieDetail) : ViewModelDetailsState()
    data class Error(val error: String) : ViewModelDetailsState()
}