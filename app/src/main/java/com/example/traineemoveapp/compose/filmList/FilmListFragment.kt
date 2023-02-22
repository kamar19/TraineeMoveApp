package com.example.traineemoveapp.compose.filmList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traineemoveapp.MainActivity.Companion.COUNT_ROWS
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.viewModel.MainActivityViewModel

@Composable fun FilmListFragment(modifier: Modifier = Modifier, viewModel: MainActivityViewModel, onClickToDetailScreen: (Int) -> Unit = {}) {
        Column(
                modifier = modifier,
                verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchField(
                    textSeach = "" )
            CategoryFilmsView(viewModel.getAllGenres())
            FilmListGrid(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_normal)),
                    onClickToDetailScreen = onClickToDetailScreen,
                    viewModel = viewModel,
            )
        }
    //}
}


@Composable
private fun SearchField(textSeach: String)
 {
    TextField(
            modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
            value = textSeach,
            onValueChange = {
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 13.sp),
            trailingIcon = {
                Icon(Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_text),
                        modifier = Modifier
                                .padding(20.dp, 10.dp)
                                .size(20.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    imeAction = ImeAction.Search,),
            colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent)
    )
}

@Composable fun FilmListGrid(
        modifier: Modifier = Modifier,
        viewModel: MainActivityViewModel,
        onClickToDetailScreen: (Int) -> Unit = {},
) {
    LazyVerticalGrid(
            columns = GridCells.Fixed(COUNT_ROWS),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_horizontal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_vertical)),
    ) {
        items(
                items = viewModel.uiState.value.films ,
        ) { film ->
            FilmListItem(film = film, viewModel.getImage(film.id_photo)) {
                film.id?.let { onClickToDetailScreen(it) }
            }
        }
    }
}


@Composable
fun CategoryFilmsView(categoryFilms: MutableList<Genre>) {
    LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
        items(items = categoryFilms,) {
            category ->
            GenresChip(item = category)
        }
    }
}


    @OptIn(ExperimentalMaterialApi::class) @Composable
    fun GenresChip(item: Genre) {
        Chip(
                onClick = { },
                modifier = Modifier
                        .padding(end = 6.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary),
                colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent)
        ) {
            Text(text = item.name)
        }
    }


