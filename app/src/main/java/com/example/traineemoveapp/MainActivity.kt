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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = FromJsonRepository(applicationContext)
        setContent {
            TraineeMoveAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    FilmAppScreen(repository)
                }
            }
        }
    }

    companion object {
            const val LIST_FILMS = "LIST_FILMS"
            const val DETAIL_FILM = "DETAIL_FILM"
            const val FILM_ID = "FILM_ID"
            const val COUNT_ROWS = 2
            const val DETAIL_IMAGE_HEIGHT = 250
            const val TITLE_TEXT_MAX_LINES = 2
            const val DETAIL_TEXT_MAX_LINES = 8
            const val ASSET_FILE_NAME = "data.json"
    }
}