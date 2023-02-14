package com.example.traineemoveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.traineemoveapp.ui.theme.TraineeMoveAppTheme
import com.example.traineemoveapp.viewModel.MainActivityViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.traineemoveapp.compose.filmslist.FilmListScreen
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TraineeMoveAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting(viewModel)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI elements
                }
            }
        }
    }

    @Composable fun Greeting(viewModel: MainActivityViewModel) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(Modifier.fillMaxWidth()) {
                FilmListScreen(
                        viewModel.films,
                        modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}