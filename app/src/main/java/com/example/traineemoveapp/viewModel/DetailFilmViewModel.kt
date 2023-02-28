package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailFilmViewModel(val filmRepository: FilmRepository, idFilm: Int) : ViewModel() {
    data class ViewModelDetailState(val film: Film)

    private val _uiState = MutableStateFlow(filmRepository.getFilm(idFilm)?.let { ViewModelDetailState(it) })
    val uiState: StateFlow<ViewModelDetailState> get() = _uiState.asStateFlow() as StateFlow<ViewModelDetailState>

    fun getImage(idFilm: Int): Int {
        val idImage = filmRepository.getFilm(idFilm)?.id_photo
        when (idImage) {
            1 -> return R.drawable.image1
            2 -> return R.drawable.image2
            3 -> return R.drawable.image3
            4 -> return R.drawable.image4
            else -> return R.drawable.image1
        }
    }

    fun getFilmGenres(filmGenres: MutableList<Int>): MutableList<Genre> {
        val allGenres = filmRepository.getAllGenre()
        return allGenres.filter { filmGenres.contains(it.id) } as MutableList<Genre>
    }

    fun getFilmActors(actors: MutableList<Int>): MutableList<Actor> {
        val allActors = filmRepository.getAllActor()
        return allActors.filter { actors.contains(it.id) } as MutableList<Actor>
    }

    fun getActorImage(id_actor: Int): Int {
        val idImage = filmRepository.getActor(id_actor)?.id
        when (idImage) {
            1 -> return R.drawable.act1
            2 -> return R.drawable.act2
            3 -> return R.drawable.act3
            4 -> return R.drawable.act4
            5 -> return R.drawable.act5
            6 -> return R.drawable.act6
            7 -> return R.drawable.act6
            else -> return R.drawable.act1
        }
    }
}