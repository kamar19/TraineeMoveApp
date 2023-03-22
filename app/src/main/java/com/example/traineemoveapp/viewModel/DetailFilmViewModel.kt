package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.viewModel.states.ViewModelDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.traineemoveapp.utils.Result
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor(val filmRepository: RemoteRepository) : ViewModel() {
    var idFilm: Long = 0
    private var scope = viewModelScope
    private val _uiFilmDetailState = MutableStateFlow<ViewModelDetailsState>(ViewModelDetailsState.Loading)
    val uiFilmDetailState: StateFlow<ViewModelDetailsState> get() = _uiFilmDetailState.asStateFlow()

    init {
        if (idFilm > 0) {
            updateFilm(idFilm)
        }
    }

    fun updateFilm(idFilm: Long) {
        scope.launch {
            val actorsResult = filmRepository.loadActorFromNET(idFilm)
            val actors: List<Actor>
            when (actorsResult) {
                is Result.Success -> {
                    actors = actorsResult.result
                }
                else -> actors = arrayListOf()
            }
            val filmResult = filmRepository.loadMovieFromNET(idFilm)
            when (filmResult) {
                is Result.Success -> _uiFilmDetailState.value = ViewModelDetailsState.Success(
                    filmResult.result, actors
                )
                else -> _uiFilmDetailState.value = ViewModelDetailsState.Error("Search movie from server error")
            }
        }
    }
}