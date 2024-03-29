package com.example.traineemoveapp.compose.filmList

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traineemoveapp.MainActivity.Companion.COUNT_ROWS
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.viewModel.MainActivityViewModel
import com.example.traineemoveapp.viewModel.states.ViewModelListState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilmListFragment(viewModel: MainActivityViewModel,
                     titleText: String,
                     onClickToDetailScreen: (Long) -> Unit = {},
                     onClickToSelectCategory: (Int) -> Unit = {}
) {
    val state = viewModel.uiFilmsState.collectAsState()
    var isRefreshing = false
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.refresh() })

    when (state.value) {
        is ViewModelListState.Loading -> ProgressIndicator()
        is ViewModelListState.Error -> ErrorBox()
        is ViewModelListState.Refreshing -> {
            isRefreshing = (state.value as ViewModelListState.Refreshing).isRefreshing
        }
        is ViewModelListState.Success -> {
            Column(
                modifier = Modifier.pullRefresh(pullRefreshState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchField(viewModel)
                Text(
                    text = titleText,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                )
                CategoryFilmsView(
                    viewModel, state, onClickToSelectCategory
                )
                PullRefreshIndicator(
                    isRefreshing, pullRefreshState, Modifier.align(Alignment.CenterHorizontally)
                )
                FilmListGrid(
                    state,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_normal)),
                    onClickToDetailScreen = onClickToDetailScreen,
                )
            }
        }
    }
}

@Composable
fun ErrorBox() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = stringResource(R.string.error_net_text), modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Composable
fun ProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator()
        Text(
            text = stringResource(R.string.load_net_text), modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun SearchField(viewModel: MainActivityViewModel) {
    val text = remember {
        mutableStateOf("")
    }
    TextField(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp),
        onValueChange = {
            text.value = it
            viewModel.changeSearchText(text.value)
            viewModel.findFilms()
        },
        value = text.value,
        textStyle = TextStyle(color = Color.Black, fontSize = 13.sp),
        trailingIcon = {
            Icon(
                Icons.Default.Search,
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
            imeAction = ImeAction.Search,
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun FilmListGrid(
        state: State<ViewModelListState>,
        modifier: Modifier = Modifier,
        onClickToDetailScreen: (Long) -> Unit = {},
) {
    when (state.value) {
        is ViewModelListState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(COUNT_ROWS),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_horizontal)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_vertical)),
            ) {
                if (state.value is ViewModelListState.Success) {
                    val films = (state.value as ViewModelListState.Success).listFilm
                    items(
                        items = films,
                    ) { film ->
                        FilmListItem(film = film) {
                            film.id?.let { onClickToDetailScreen(it) }
                        }
                    }
                }
            }
        }
        else -> {}
    }
}

@Composable
fun CategoryFilmsView(viewModel: MainActivityViewModel,
                      state: State<ViewModelListState>,
                      onClickToSelectCategory: (Int) -> Unit = {}
) {
    when (state.value) {
        is ViewModelListState.Success -> {
            val genres = (state.value as ViewModelListState.Success).genres
            LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
                items(items = genres) { category ->
                    category.genreId?.let {
                        val textChipRememberOneState = remember {
                            mutableStateOf(viewModel.checkSelectedGenre(it))
                        }
                        GenresChip(item = category, isSelected = textChipRememberOneState.value, {
                            onClickToSelectCategory(it)
                            textChipRememberOneState.value = !textChipRememberOneState.value
                        })
                    }
                }
            }
        }
        else -> {}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenresChip(item: Genre, isSelected: Boolean, onClickToSelectCategory: () -> Unit = {}) {
    Chip(
        onClick = onClickToSelectCategory,
        modifier = Modifier.padding(end = 6.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary),
        colors = ChipDefaults.chipColors(backgroundColor = if (isSelected) Color.LightGray else Color.Transparent)
    ) {
        Text(text = item.name)
    }
}
