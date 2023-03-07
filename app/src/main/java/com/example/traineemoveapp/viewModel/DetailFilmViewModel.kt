package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.viewModel.states.ViewModelActorsState
import com.example.traineemoveapp.viewModel.states.ViewModelDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.traineemoveapp.utils.Result

class DetailFilmViewModel(val filmRepository: RemoteRepository, idFilm: Long) : ViewModel() {
    private var scope = viewModelScope
    private val _uiFilmDetailState = MutableStateFlow<ViewModelDetailsState>(ViewModelDetailsState.Loading)
    val uiFilmDetailState: StateFlow<ViewModelDetailsState> get() = _uiFilmDetailState.asStateFlow()

    private val _uiActorState = MutableStateFlow<ViewModelActorsState>(ViewModelActorsState.Loading)
    val uiActorState: StateFlow<ViewModelActorsState> get() = _uiActorState.asStateFlow()

    private val _errorDetailState = MutableStateFlow<String>("")
    val errorDetailState: StateFlow<String> get() = _errorDetailState.asStateFlow()

    init {
        scope.launch {
            startValue(idFilm)
        }
    }

    suspend fun startValue(idFilm: Long) {
        val filmResult = filmRepository.loadMovieFromNET(idFilm)
        if (filmResult is Result.Success) {
            _uiFilmDetailState.value = ViewModelDetailsState.Success((filmResult as Result.Success).result)
        } else {
            _errorDetailState.value = (filmResult as Result.Error).result.message.toString()
        }
        val actorsResult = filmRepository.loadActorFromNET(idFilm)
        if (actorsResult is Result.Success) {
            _uiActorState.value = ViewModelActorsState.Success((actorsResult as Result.Success).result)
        }
    }
}