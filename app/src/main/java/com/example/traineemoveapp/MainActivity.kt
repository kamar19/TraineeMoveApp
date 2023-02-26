package com.example.traineemoveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.example.traineemoveapp.ui.theme.TraineeMoveAppTheme
import com.example.traineemoveapp.navigation.FilmAppScreen
import com.example.traineemoveapp.repository.FromJsonRepository
import com.example.traineemoveapp.repository.GenreRepositoryImpl
import com.example.traineemoveapp.viewModel.MainActivityViewModel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stream = applicationContext.assets.open(ASSET_FILE_NAME)
        val gson = Gson()
        val filmRepository = FromJsonRepository(stream, gson)
        val genreRepository = GenreRepositoryImpl()

        val viewModel = MainActivityViewModel(filmRepository, genreRepository)

        setContent {
            TraineeMoveAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    FilmAppScreen(viewModel)
                }
            }
        }
    }

    companion object {
            const val LIST_FILMS = "LIST_FILMS"
            const val DETAIL_FILM = "DETAIL_FILM"
            const val FILM_ID = "FILM_ID"
            const val COUNT_ROWS = 2
            const val DETAIL_CARD_HEIGHT = 400
            const val DETAIL_IMAGE_HEIGHT = 230
            const val DETAIL_IMAGE_WIDTH = 170
            const val TITLE_TEXT_MAX_LINES = 2
            const val DETAIL_TEXT_MAX_LINES = 8
            const val ASSET_FILE_NAME = "data.json"
    }
}