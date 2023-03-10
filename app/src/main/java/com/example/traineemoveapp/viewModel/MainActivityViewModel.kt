package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.MainActivity.Companion.SEARCH_PRINCIPLE
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.viewModel.states.ViewModelListState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.traineemoveapp.utils.Result

class MainActivityViewModel(val filmRepository: RemoteRepository) : ViewModel() {
    private val selectedGenres: MutableList<Int> = mutableListOf()
    var genres: List<Genre> = mutableListOf()
    var allFilms: List<Film> = mutableListOf()
    private var searchText: String = ""
    private var scope = viewModelScope
    private val _uiFilmsState = MutableStateFlow<ViewModelListState>(ViewModelListState.Loading)
    val uiFilmsState: StateFlow<ViewModelListState> get() = _uiFilmsState.asStateFlow()

    init {
        scope.launch {
            startValue()
        }
    }

    fun findFilms() {
        if (searchText.length > 0) {
            changeFilms(allFilms.filter {
                it.genres.containsAll(getSelectedGenres()) && it.title.contains(
                    searchText, true
                )
            } as MutableList<Film>)
        } else {
            changeFilms(allFilms.filter { it.genres.containsAll(getSelectedGenres()) } as MutableList<Film>)
        }
    }

    fun changeSearchText(newText: String) {
        searchText = newText
    }

    fun checkSelectedGenre(idGenre: Int): Boolean {
        return selectedGenres.contains(idGenre)
    }

    @JvmName("getSelectedGenres1")
    fun getSelectedGenres(): MutableList<Int> {
        return selectedGenres
    }

    fun updateSelectedGenres(idGenre: Int) {
        if (selectedGenres.contains(idGenre)) {
            selectedGenres.removeIf { it == idGenre }
        } else {
            selectedGenres.add(idGenre)
        }
    }

    fun changeFilms(newfilms: MutableList<Film>) {
        viewModelScope.launch {
            _uiFilmsState.emit(ViewModelListState.Success(newfilms, genres))
        }
    }

    suspend fun startValue() {
        val allFilmsResult = filmRepository.loadMoviesFromNET(SEARCH_PRINCIPLE)
        val newGenresResult = filmRepository.loadGenreFromNET()
        if (newGenresResult is Result.Success) {
            genres = newGenresResult.result
        }
        if (allFilmsResult is Result.Success) {
            allFilms = allFilmsResult.result
            _uiFilmsState.value = ViewModelListState.Success(allFilms, genres)
        }
    }

}