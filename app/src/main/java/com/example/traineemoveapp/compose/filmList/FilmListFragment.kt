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
import com.example.traineemoveapp.repository.FilmRepository
import com.example.traineemoveapp.viewModel.MainActivityViewModel

@Composable fun FilmListFragment(modifier: Modifier = Modifier, filmRepositoryImpl: FilmRepository, onClickToDetailScreen: (Int) -> Unit = {}) {
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        FilmListGrid(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_normal)),
                onClickToDetailScreen = onClickToDetailScreen,
                filmRepositoryImpl = filmRepositoryImpl,
        )
    }
}

@Composable fun FilmListGrid(
        modifier: Modifier = Modifier,
        filmRepositoryImpl: FilmRepository,
        onClickToDetailScreen: (Int) -> Unit = {},
) {
    val viewModel = MainActivityViewModel(filmRepositoryImpl)
    LazyVerticalGrid(
            columns = GridCells.Fixed(COUNT_ROWS),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_horizontal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_vertical)),
    ) {
        items(
                items = filmRepositoryImpl.getAllFils(),
        ) { film ->
            FilmListItem(film = film, viewModel.getImage(film.id_photo)) {
                film.id?.let { onClickToDetailScreen(it) }
            }
        }
    }
}
