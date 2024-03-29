package com.example.traineemoveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.traineemoveapp.di.factory.DetailsViewModelFactory
import com.example.traineemoveapp.di.factory.MainActivityViewModelFactory
import com.example.traineemoveapp.navigation.FilmAppScreen
import com.example.traineemoveapp.ui.theme.TraineeMoveAppTheme
import com.example.traineemoveapp.viewModel.DetailFilmViewModel
import com.example.traineemoveapp.viewModel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: MainActivityViewModelFactory

    @Inject
    lateinit var detailsViewModelFactory: DetailsViewModelFactory
    lateinit var viewModel: MainActivityViewModel
    lateinit var viewModelDetail: DetailFilmViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        viewModelDetail = ViewModelProvider(
            this, detailsViewModelFactory
        ).get(DetailFilmViewModel::class.java)
        setContent {
            TraineeMoveAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    FilmAppScreen(viewModel, viewModelDetail)
                }
            }
        }
    }

    companion object {
        const val LIST_FILMS = "LIST_FILMS"
        const val DETAIL_FILM = "DETAIL_FILM"
        const val FILM_ID = "FILM_ID"
        const val COUNT_ROWS = 2
        const val TITLE_TEXT_MAX_LINES = 2
        const val DETAIL_TEXT_MAX_LINES = 8
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val apiKey = "f1eaa713b8b88ceef63a9cd8be1f7920"
        const val BASE_URL_MOVIES = "https://image.tmdb.org/t/p/original"
        const val SEARCH_PRINCIPLE = "now_playing"
    }
}
























