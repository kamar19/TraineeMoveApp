package com.example.traineemoveapp.viewModel.states

import com.example.traineemoveapp.model.Actor

open class ViewModelActorsState {
    object Loading : ViewModelActorsState()
    data class Success(val actorList: List<Actor>) : ViewModelActorsState()
    data class Error(val error: String) : ViewModelActorsState()
}