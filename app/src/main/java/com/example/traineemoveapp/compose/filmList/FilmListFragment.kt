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

@Composable fun FilmListFragment(modifier: Modifier = Modifier, viewModel: MainActivityViewModel, titleText:String , onClickToDetailScreen: (Int) -> Unit = {},  onClickToSelectCategory: (Int) -> Unit = {}, onInputText: (String) -> Unit = {} ) {
        Column(
                modifier = modifier,
                verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchField(viewModel, onInputText)
            Text(text = titleText, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start,  fontSize = 16.sp, maxLines = 2, modifier = Modifier
                    .fillMaxWidth()
                    .padding( vertical = 10.dp, horizontal = 20.dp)
            )
            CategoryFilmsView(viewModel, onClickToSelectCategory)
            FilmListGrid(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_normal)),
                    onClickToDetailScreen = onClickToDetailScreen,
                    viewModel = viewModel,
            )
        }
}

@SuppressLint("StateFlowValueCalledInComposition") @Composable
private fun SearchField(viewModel:MainActivityViewModel, onInputText: (String) -> Unit = {} )
 {
     val text = remember {
         mutableStateOf("")
     }
    TextField(
            modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
            onValueChange = {
                text.value = it
                onInputText(text.value)
            },
            value = text.value,
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
    val myItems by viewModel.uiState.collectAsState()
    LazyVerticalGrid(
            columns = GridCells.Fixed(COUNT_ROWS),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_horizontal)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.film_item_vertical)),
    ) {
        items(
                items = myItems.films,
        ) { film ->
            FilmListItem(film = film, viewModel.getImage(film.id_photo)) {
                film.id?.let { onClickToDetailScreen(it) }
            }
        }
    }
}

@Composable
fun CategoryFilmsView(viewModel:MainActivityViewModel, onClickToSelectCategory: (Int) -> Unit = {} ) {
    LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
        items(items = viewModel.getAllGenres()) {
            category ->
            category.id?.let {
                val textChipRememberOneState = remember {
                    mutableStateOf(viewModel.checkSelectedGenre(it))
                }
                GenresChip(item = category,  isSelected = textChipRememberOneState.value, {
                    onClickToSelectCategory(it)
                    textChipRememberOneState.value = !textChipRememberOneState.value
                })
            }
        }
    }
}

    @OptIn(ExperimentalMaterialApi::class) @Composable
    fun GenresChip(item: Genre, isSelected:Boolean, onClickToSelectCategory: () -> Unit = {}) {
        val onClickToSelectCategory2: () -> Unit = {
            onClickToSelectCategory()
        }
        Chip(
                onClick = onClickToSelectCategory2,
                modifier = Modifier
                        .padding(end = 6.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary),
                colors = ChipDefaults.chipColors(backgroundColor = if (isSelected) Color.LightGray else Color.Transparent, disabledBackgroundColor =  Color.Blue,disabledLeadingIconContentColor = Color.Red, leadingIconContentColor  = Color.Red)
        ) {
            Text(text = item.name)
        }
    }


