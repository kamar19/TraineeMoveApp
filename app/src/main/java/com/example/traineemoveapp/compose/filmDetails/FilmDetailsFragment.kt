package com.example.traineemoveapp.compose.filmDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_TEXT_MAX_LINES
import com.example.traineemoveapp.MainActivity.Companion.TITLE_TEXT_MAX_LINES
import com.example.traineemoveapp.compose.filmList.AgeRatingComponent
import com.example.traineemoveapp.compose.filmList.RatingBar
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.viewModel.DetailFilmViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable fun FilmDetailsFragment(
        modifier: Modifier = Modifier,
        viewModel: DetailFilmViewModel,
        idFilm: Int,
) {
    val film = viewModel.film
    val configuration = LocalConfiguration.current
    film?.let {
            BottomSheetScaffold(
                    sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 16.dp),
                    sheetPeekHeight = configuration.screenHeightDp.dp - (configuration.screenHeightDp.dp / 3),
                    sheetContent = {
                        Row(modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp, bottom = 2.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                        Column() {
                            Row(modifier = Modifier.padding(start = 10.dp, end = 5.dp, bottom = 2.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                CategoryDetailsView(viewModel.getFilmGenres(film.genre_ids as MutableList))
                                Text(
                                        text = film.date_publication,
                                        modifier = Modifier.padding(10.dp), fontSize = 12.sp,
                                )
                            }
                            Text(text = it.name, maxLines = TITLE_TEXT_MAX_LINES,  fontSize = 22.sp, fontWeight = Bold, modifier = Modifier.padding(10.dp).align(Alignment.Start))
                            RatingBar(rating = film.rating, spaceBetween = 3.dp, size = 20.dp, modifier = Modifier.padding(10.dp).align(Alignment.Start))
                        }
                            Box(modifier =  Modifier, contentAlignment = Alignment.TopEnd,  ) {
                                AgeRatingComponent(ageRating = film.pg)
                            }
                        }
                    Text(text = it.description, textAlign = TextAlign.Start, maxLines = DETAIL_TEXT_MAX_LINES,  modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp).align(Alignment.Start))
        }
            ) {
                FilmImageDetails(model = viewModel.getImage(idFilm), contentDescription = stringResource(com.example.traineemoveapp.R.string.a11y_film_item_image))
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
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)))
}
