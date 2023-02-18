package com.example.traineemoveapp.compose.filmslist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.traineemoveapp.model.Film

@Composable
fun FilmListScreen(
        films: List<Film>,
        modifier: Modifier = Modifier,
        onFilmClick: (Film) -> Unit = {},
) {
    LazyVerticalGrid(
            columns = GridCells.Fixed(2),
    ) {
        items(
                items = films,
         ) { film ->
            FilmListItem(film = film) {
                onFilmClick(film)
            }
        }
    }
}
