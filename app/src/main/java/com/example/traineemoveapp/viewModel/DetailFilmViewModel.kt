package com.example.traineemoveapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.R
import com.example.traineemoveapp.data.remote.DTO
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.FilmRepository
import com.example.traineemoveapp.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailFilmViewModel(val filmRepository: RemoteRepository, idFilm: Long) : ViewModel() {
    private var scope = viewModelScope
    private val _uiState = MutableStateFlow<ViewModelDetailsState>(ViewModelDetailsState.Loading)
    val uiState: StateFlow<ViewModelDetailsState> get() = _uiState.asStateFlow()

    init {
        scope.launch {
            Log.v("test_log","MainActivityViewModel - init startValue")
            startValue(idFilm)
        }
        Log.v("test_log","MainActivityViewModel - init end")
    }

    suspend fun startValue(idFilm: Long) {
        val film = filmRepository.loadMovieFromNET(idFilm)
        _uiState.value = ViewModelDetailsState.Success(film)
        //loadGenres()
    }

    fun getFilmGenres(filmGenres: MutableList<Int>): MutableList<Genre> {
        val allGenres = filmRepository.allGenres
        return allGenres.filter { filmGenres.contains(it.id) } as MutableList<Genre>
    }

    fun getFilmActors(actors: MutableList<Int>): MutableList<Actor> {
        val allActors = filmRepository.allActors
        return allActors.filter { actors.contains(it.id) } as MutableList<Actor>
    }

//    fun getActorImage(id_actor: Int): Int {
//        val idImage = filmRepository.getActor(id_actor)?.id
//        when (idImage) {
//            1 -> return R.drawable.act1
//            2 -> return R.drawable.act2
//            3 -> return R.drawable.act3
//            4 -> return R.drawable.act4
//            5 -> return R.drawable.act5
//            6 -> return R.drawable.act6
//            7 -> return R.drawable.act6
//            else -> return R.drawable.act1
//        }
//    }
}