package com.example.traineemoveapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traineemoveapp.MainActivity.Companion.SEARCH_PRINCIPLE
import com.example.traineemoveapp.model.*
import com.example.traineemoveapp.data.repository.Repository
import com.example.traineemoveapp.viewModel.states.ViewModelListState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.traineemoveapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
        val repository: Repository
) : ViewModel() {
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

    fun refresh() {
        scope.launch {
            allFilms = updateDatasFromNet()
            changeFilms(allFilms as MutableList<Film>, genres)
        }
    }

    fun findFilms() {
        if (searchText.length > 0) {
            changeFilms(allFilms.filter {
                it.genres.containsAll(getSelectedGenres()) && it.title.contains(
                    searchText, true
                )
            } as MutableList<Film>, genres)
        } else {
            changeFilms(allFilms.filter { it.genres.containsAll(getSelectedGenres()) } as MutableList<Film>,
                genres)
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

    fun changeFilms(newfilms: MutableList<Film>, genres: List<Genre>) {
        viewModelScope.launch {
            _uiFilmsState.emit(ViewModelListState.Success(newfilms, genres))
        }
    }

    suspend fun startValue() {
        val listGenreEntity: List<GenreEntity> = loadGenresList()
        genres = repository.repositoryDB.convertGenreEntityToGenre(listGenreEntity)
        val listRelationMovie: List<FilmRelation> = loadFilmsList()
        if (listRelationMovie.size > 0) {
            allFilms = repository.repositoryDB.convertFilmRelationToFilm(listRelationMovie)
        } else {
            allFilms = updateDatasFromNet()
        }
        changeFilms(allFilms as MutableList<Film>, genres)
    }

    suspend fun updateDatasFromNet(): List<Film> {
        val newGenresResult = repository.remoteRepository.loadGenreFromNET()
        val allFilmsResult = repository.remoteRepository.loadMoviesFromNET(SEARCH_PRINCIPLE)
        if (newGenresResult is Result.Success) {
            genres = newGenresResult.result
        }
        if (allFilmsResult is Result.Success) {
            val filmRelation: MutableList<FilmRelation> = arrayListOf()
            allFilmsResult.result.forEach {
                filmRelation.add(
                    FilmRelation(
                        FilmEntity(
                            id = it.id,
                            title = it.title,
                            posterPicture = it.posterPicture,
                            ratings = it.ratings,
                            overview = it.overview,
                            adult = it.adult
                        ), repository.repositoryDB.convertGenreyToGenreEntity(genres)
                    )
                )
            }
            repository.repositoryDB.saveFilmsToDB(allFilmsResult.result)
            repository.repositoryDB.saveGenreToDB(allFilmsResult.result, genres)
            allFilms = allFilmsResult.result
            return allFilmsResult.result
        } else return arrayListOf()
    }

    suspend fun loadFilmsList(): List<FilmRelation> = withContext(Dispatchers.IO) {
        repository.repositoryDB.filmDAO.getFilms()
    }

    suspend fun loadGenresList(): List<GenreEntity> = withContext(Dispatchers.IO) {
        repository.repositoryDB.filmDAO.getAllGenre()
    }

}