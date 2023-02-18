package com.example.traineemoveapp.compose.filmList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.traineemoveapp.MainActivity.Companion.COUNT_ROWS
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.repository.FilmRepository
import com.example.traineemoveapp.viewModel.MainActivityViewModel

@Composable fun FilmListFragment(modifier: Modifier = Modifier, filmRepositoryImpl: FilmRepository, onClickToDetailScreen: (Int) -> Unit = {}) {
    val viewModel = MainActivityViewModel(filmRepositoryImpl)
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        FilmListGrid(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_normal)),
                viewModel = viewModel,
                films = viewModel.getFilms(),
                onClickToDetailScreen = onClickToDetailScreen,
        )
    }
}

@Composable fun FilmListGrid(
        modifier: Modifier = Modifier,
        films: List<Film>,
        viewModel: MainActivityViewModel,
        onClickToDetailScreen: (Int) -> Unit = {},
) {
    LazyVerticalGrid(
            columns = GridCells.Fixed(COUNT_ROWS),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_horizontal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_vertical)),
    ) {
        items(
                items = films,
        ) { film ->
            FilmListItem(film = film, viewModel.getImage(film.idPhoto) ) {
                film.id?.let { onClickToDetailScreen(it) }
            }
        }
    }
}
