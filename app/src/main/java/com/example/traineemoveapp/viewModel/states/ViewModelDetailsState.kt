package com.example.traineemoveapp.viewModel.states

import com.example.traineemoveapp.data.remote.DTO
import com.example.traineemoveapp.model.Actor

open class ViewModelDetailsState {
    object Loading : ViewModelDetailsState()
    data class Success(val film: DTO.MovieDetail, val actors: List<Actor>) : ViewModelDetailsState()
    data class Error(val error: String) : ViewModelDetailsState()
}