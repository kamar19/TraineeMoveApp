package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.MainActivity.Companion.SEARCH_PRINCIPLE
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.viewModel.states.ViewModelGenresListState
import com.example.traineemoveapp.viewModel.states.ViewModelInputState
import com.example.traineemoveapp.viewModel.states.ViewModelListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.traineemoveapp.utils.Result

class MainActivityViewModel(val filmRepository: RemoteRepository) : ViewModel() {
    private val selectedGenres: MutableList<Int> = mutableListOf()
    var allFilms: List<Film> = mutableListOf()
    private var scope = viewModelScope

    private val _uiInputState = MutableStateFlow(ViewModelInputState(""))
    val uiInputState: StateFlow<ViewModelInputState> get() = _uiInputState.asStateFlow()

    private val _uiFilmsState = MutableStateFlow<ViewModelListState>(ViewModelListState.Loading)
    val uiFilmsState: StateFlow<ViewModelListState> get() = _uiFilmsState.asStateFlow()

    private val _uiGenresState = MutableStateFlow<ViewModelGenresListState>(ViewModelGenresListState.Loading)
    val uiGenresState: StateFlow<ViewModelGenresListState> get() = _uiGenresState.asStateFlow()

    private val _errorState = MutableStateFlow<String>("")
    val errorState: StateFlow<String> get() = _errorState.asStateFlow()

    init {
        scope.launch {
            startValue()
        }
    }

    fun findFilms() {
        if (uiInputState.value.searchText.length > 0) {
            changeFilms(allFilms.filter {
                it.genres.containsAll(getSelectedGenres()) && it.title.contains(
                    uiInputState.value.searchText, true
                )
            } as MutableList<Film>)
        } else {
            changeFilms(allFilms.filter { it.genres.containsAll(getSelectedGenres()) } as MutableList<Film>)
        }
    }

    fun changeSearchText(newText: String) {
        _uiInputState.value.searchText = newText
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
            _uiFilmsState.emit(ViewModelListState.Success(newfilms))
        }
    }

    suspend fun startValue() {
        val allFilmsResult = filmRepository.loadMoviesFromNET(SEARCH_PRINCIPLE)
        if (allFilmsResult is Result.Success) {
            _uiFilmsState.value = ViewModelListState.Success((allFilmsResult as Result.Success).result)
            allFilms = (allFilmsResult as Result.Success).result
        } else {
            _errorState.value = (allFilmsResult as Result.Error).result.message.toString()
        }
        loadGenres()
    }

    suspend private fun loadGenres() = withContext(Dispatchers.IO) {
        val newGenresResult = filmRepository.loadGenreFromNET()
        if (newGenresResult is Result.Success) {
            _uiGenresState.value = ViewModelGenresListState.Success((newGenresResult as Result.Success).result)
        } else {
            _errorState.value = (newGenresResult as Result.Error).result.message.toString()
        }
    }
}
