package com.example.traineemoveapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.MainActivity.Companion.SEARCH_PRINCIPLE
import com.example.traineemoveapp.model.*
import com.example.traineemoveapp.repository.RemoteRepository
import com.example.traineemoveapp.repository.RepositoryDB
import com.example.traineemoveapp.viewModel.states.ViewModelListState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.traineemoveapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityViewModel(val repositoryRemote: RemoteRepository, val repositoryDB: RepositoryDB) : ViewModel() {
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
    fun getSelectedGenres(): List<Int> {
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
        loadGenresList()
        loadFilmsList()

        val newGenresResult = repositoryRemote.loadGenreFromNET()
        val allFilmsResult = repositoryRemote.loadMoviesFromNET(SEARCH_PRINCIPLE)
        if (newGenresResult is Result.Success) {
            genres = newGenresResult.result
        }
        if (allFilmsResult is Result.Success) {
            allFilms = allFilmsResult.result
            _uiFilmsState.value = ViewModelListState.Success(allFilms, genres)
            repositoryDB.saveFilmsToDB(allFilms, genres)
        }
    }

    suspend fun loadFilmsList(){
        var moviesFromDb: List<Film>
        withContext(Dispatchers.IO) {
            val listRelationMovie: List<FilmRelation> = repositoryDB.filmDAO.getFilms()
            val genres: List<Genre> = repositoryDB.filmDAO.getAllGenre()

            Log.v("test_log", "listRelationMovie = ${listRelationMovie.size}")
            if (listRelationMovie != null && listRelationMovie.size > 0) {
                moviesFromDb = repositoryDB.convertFilmRelationToFilm(listRelationMovie, genres)
                moviesFromDb.sortedBy { it.ratings }
                changeFilms(moviesFromDb as MutableList<Film>)
            }
        }
    }

    suspend fun loadGenresList(){
//        var genres: List<Genre>
        withContext(Dispatchers.IO) {
            var genresNew: List<Genre> = repositoryDB.filmDAO.getAllGenre()
            Log.v("test_log", "genresNew = ${genresNew.size}")

            if (genresNew != null && genresNew.size > 0) {
                 genres = genresNew
//                genres = newGenresResult.result
            }
        }
    }

}