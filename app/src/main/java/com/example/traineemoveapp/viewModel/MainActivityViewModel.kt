package com.example.traineemoveapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.MainActivity.Companion.SEARCH_PRINCIPLE
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(val filmRepository: RemoteRepository) : ViewModel() {
    private val selectedGenres: MutableList<Int> = mutableListOf()
    var allFilms: MutableList<Film> = mutableListOf()
    private var scope = viewModelScope

    data class ViewModelInputState(var searchText: String)
    private val _uiInputState = MutableStateFlow(ViewModelInputState(""))
    val uiInputState: StateFlow<ViewModelInputState> get() = _uiInputState.asStateFlow()


    private val _uiState = MutableStateFlow<ViewModelListState>(ViewModelListState.Loading)
    val uiState: StateFlow<ViewModelListState> get() = _uiState.asStateFlow()

    private val _uiGenresState = MutableStateFlow<ViewModelGenresListState>(ViewModelGenresListState.Loading)
    val uiGenresState: StateFlow<ViewModelGenresListState> get() = _uiGenresState.asStateFlow()

        init {
        scope.launch {
            Log.v("test_log","MainActivityViewModel - init startValue")
            startValue()
        }
            Log.v("test_log","MainActivityViewModel - init end")
        }

    fun findFilms(){
        if (uiInputState.value.searchText.length > 0) {
            changeFilms(allFilms.filter { it.genres.containsAll(getSelectedGenres()) &&  it.title.contains(uiInputState.value.searchText, true) } as MutableList<Film>)
        } else {
            changeFilms(allFilms.filter { it.genres.containsAll(getSelectedGenres())} as MutableList<Film>)
        }
    }

    fun changeSearchText(newText:String){
        _uiInputState.value.searchText =  newText
    }

    fun checkSelectedGenre(idGenre: Int):Boolean {
        return selectedGenres.contains(idGenre)
    }

    @JvmName("getSelectedGenres1")
    fun getSelectedGenres(): MutableList<Int> {
        Log.v("test_log","getSelectedGenres - selectedGenres.size = " + selectedGenres.size.toString())
        return selectedGenres
    }

    fun updateSelectedGenres(idGenre:Int) {
        if (selectedGenres.contains(idGenre)) {
            selectedGenres.removeIf { it == idGenre }
        } else {
            selectedGenres.add (idGenre)
        }
    }

    fun findGenre(idGenre:Int): Genre? {
        return (uiGenresState.value as ViewModelGenresListState.Success).genresList.find { it.id == idGenre }
    }

    fun changeFilms(newfilms: MutableList<Film>) {
        viewModelScope.launch {
            _uiState.emit(ViewModelListState.Success(newfilms))
        }
    }

    suspend fun startValue() {
        allFilms = filmRepository.loadMoviesFromNET(SEARCH_PRINCIPLE) as MutableList<Film>
        _uiState.value = ViewModelListState.Success(allFilms)
        loadGenres()
    }

    suspend private fun loadGenres()  {
        val allGenres: MutableList<Genre> = arrayListOf()

        viewModelScope.launch {
            allFilms.forEach { it1->
                withContext(Dispatchers.IO) {
                    val newGenres = filmRepository.loadGenreFromNET(it1.id)
                    newGenres.forEach { it2->
                        if (allGenres.size > 0) {
                            if (allGenres.findLast { it.id ==  it2.id } == null)
                                allGenres.add(it2)
                        } else {
                            allGenres.add(it2)
                        }
                    }
                }
            }
        }
        _uiGenresState.value = ViewModelGenresListState.Success(allGenres)
        }

    fun updateGenres(newGenres: MutableList<Genre>) {
        viewModelScope.launch {
            _uiGenresState.emit(ViewModelGenresListState.Success(newGenres))
        }
    }
}
