package com.example.traineemoveapp.viewModel

import com.example.traineemoveapp.data.remote.DTO
import com.example.traineemoveapp.model.Film

open class ViewModelDetailsState  {
    object Loading : ViewModelDetailsState()
    data class Success(val film: DTO.MovieDetail) : ViewModelDetailsState()
    data class Error(val error: String) : ViewModelDetailsState()
}