package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.repository.FilmRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivityViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private val genres = initGenres()
    private val selectedGenres: MutableList<Int> = mutableListOf()
    var allFilms: MutableList<Film> = mutableListOf()

    data class ViewModelInputState(var searchText: String)
    private val _uiInputState = MutableStateFlow(ViewModelInputState(""))
    val uiInputState: StateFlow<ViewModelInputState> get() = _uiInputState.asStateFlow()

    data class ViewModelListState(val films: MutableList<Film> = mutableListOf())
    private val _uiState = MutableStateFlow(ViewModelListState())
    val uiState: StateFlow<ViewModelListState> get() = _uiState.asStateFlow()

    init {
        startValue()
    }

    fun findFilmsByText(newText:String){
        if (newText.length > 0) {
            changeSearchText(newText)
            changeFilms(allFilms.filter { it.name.contains(uiInputState.value.searchText, true) } as MutableList<Film>)
        } else {
            changeSearchText("")
            changeFilms(allFilms)
        }
    }

    fun changeSearchText(newText:String){
        _uiInputState.value.searchText =  newText
    }

    fun checkSelectedGenre(idGenres: Int):Boolean {
        return selectedGenres.contains(idGenres)
    }

    @JvmName("getSelectedGenres1")
    fun getSelectedGenres(): MutableList<Int> {
        return selectedGenres
    }

    fun updateSelectedGenres(genre:Int) {
        if (selectedGenres.contains(genre)) {
            selectedGenres.removeIf { it == genre }
        } else {
            selectedGenres.add (genre)
        }
    }

    fun changeFilms(newfilms: MutableList<Film>) {
        viewModelScope.launch {
            _uiState.emit(ViewModelListState(newfilms))
        }
    }

    fun startValue() {
        allFilms = filmRepository.getAllFils()
        _uiState.value = ViewModelListState(films =  allFilms)
    }

    fun getImage(idImage: Int): Int {
        when (idImage) {
            1 -> return R.drawable.image1
            2 -> return R.drawable.image2
            3 -> return R.drawable.image3
            4 -> return R.drawable.image4
            else -> return R.drawable.image1
        }
    }
    fun getAllGenres(): MutableList<Genre> {
        return genres
    }

    fun getGenre(idGenres: Int): Genre? {
        return genres.findLast { it.id == idGenres }
    }

    private fun initGenres(): MutableList<Genre> {
        val genres: MutableList<Genre> = arrayListOf()
        genres.add(Genre(1, "боевики"))
        genres.add(Genre(2,"драмы"))
        genres.add(Genre(3,"комедии"))
        genres.add(Genre(4,"артхауз"))
        genres.add(Genre(5,"мелодрамы"))
        genres.add(Genre(6,"драмы"))
        return genres
    }

}