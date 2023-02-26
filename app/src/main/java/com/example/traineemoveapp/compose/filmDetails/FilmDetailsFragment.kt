package com.example.traineemoveapp.compose.filmDetails

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_TEXT_MAX_LINES
import com.example.traineemoveapp.MainActivity.Companion.TITLE_TEXT_MAX_LINES
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.viewModel.DetailFilmViewModel

@Composable fun FilmDetailsFragment(
        modifier: Modifier = Modifier,
        viewModel: DetailFilmViewModel,
        idFilm: Int,
) {
    val film = viewModel.film
    film?.let {
        Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                modifier = modifier.background(color = Color.Blue) //  ,
        ) {
            FilmImageDetails(model = viewModel.getImage(idFilm), contentDescription = stringResource(com.example.traineemoveapp.R.string.a11y_film_item_image), Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = com.example.traineemoveapp.R.dimen.flim_list_item_image_height)), contentScale = ContentScale.Crop)
           Column(modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .background(color = Color.Red) // MaterialTheme.colors.background
                    .fillMaxWidth()) {
                Row(modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp, bottom = 2.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    CategoryDetailsView(viewModel.getFilmGenres(film.genre_ids as MutableList))
                    Text(
                            text = film.date_publication,
                            modifier = Modifier.padding(10.dp), fontSize = 12.sp,
                    )

                }
                Column(
                        modifier = Modifier
                        )
                {
                    Text(text = it.name, textAlign = TextAlign.Center, maxLines = TITLE_TEXT_MAX_LINES, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = com.example.traineemoveapp.R.dimen.margin_normal))
                            .wrapContentWidth(Alignment.CenterHorizontally))
                    Text(text = it.description, textAlign = TextAlign.Center, maxLines = DETAIL_TEXT_MAX_LINES, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = com.example.traineemoveapp.R.dimen.margin_normal))
                            .wrapContentWidth(Alignment.CenterHorizontally))
                }
            }
        }
    }
}

@Composable fun CategoryDetailsView(filmGenres: MutableList<Genre>, onClickToSelectCategory: (Int) -> Unit = {}) {
    LazyRow(contentPadding = PaddingValues(start = 10.dp), horizontalArrangement = Arrangement.Start) {
        items(items = filmGenres) { category ->
            category.let {
                GenresDetailsChip(item = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class) @Composable fun GenresDetailsChip(item: Genre) {
    Chip(onClick = {}, modifier = Modifier
            .padding(2.dp)
            .defaultMinSize(minHeight = 20.dp), border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary), colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent)) {
        Text(text = item.name, fontSize = 12.sp)
    }
}


@Composable fun FilmImageDetails(
        model: Any?,
        contentDescription: String?,
        modifier: Modifier = Modifier,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit,
) {
    Image(painter = painterResource(id = model as Int), contentDescription = null, alignment = Alignment.TopStart, contentScale = ContentScale.FillWidth, modifier = Modifier
            .height(MainActivity.DETAIL_IMAGE_HEIGHT.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)))
}
