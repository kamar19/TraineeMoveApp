package com.example.traineemoveapp.compose.filmDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_TEXT_MAX_LINES
import com.example.traineemoveapp.MainActivity.Companion.TITLE_TEXT_MAX_LINES
import com.example.traineemoveapp.R
import com.example.traineemoveapp.compose.filmList.*
import com.example.traineemoveapp.model.Genre
import com.example.traineemoveapp.viewModel.states.ViewModelDetailsState

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilmDetailsFragment(stateFilmDetail:  State<ViewModelDetailsState>) {
    val configuration = LocalConfiguration.current
    when (stateFilmDetail.value) {
        is ViewModelDetailsState.Loading -> ProgressIndicator()
        is ViewModelDetailsState.Error -> ErrorBox()
        is ViewModelDetailsState.Success -> {
            val film = (stateFilmDetail.value as ViewModelDetailsState.Success).film
            film.let {
                BottomSheetScaffold(sheetShape = RoundedCornerShape(
                    topStart = 20.dp, topEnd = 16.dp
                ),
                    sheetPeekHeight = configuration.screenHeightDp.dp - (configuration.screenHeightDp.dp / 3),
                    sheetContent = {
                        Row(
                            modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp, bottom = 2.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Row(
                                    modifier = Modifier.padding(
                                        start = 5.dp, end = 5.dp, bottom = 2.dp, top = 15.dp
                                    ),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CategoryDetailsView(filmGenres = film.genreIds as MutableList<Genre>)
                                    Text(
                                        text = film.release_date,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .align(Alignment.CenterVertically),
                                        fontSize = 12.sp,
                                    )
                                }
                                Row() {
                                    Text(
                                        text = it.title,
                                        maxLines = TITLE_TEXT_MAX_LINES,
                                        fontSize = 25.sp,
                                        fontWeight = Bold,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth(0.7F)
                                    )
                                    AgeRatingComponent(ageRating = if (film.adult) "16+" else "13+")
                                }
                                RatingBar(
                                    rating = film.vote_average / 2,
                                    spaceBetween = 3.dp,
                                    size = 20.dp,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .align(Alignment.Start)
                                )
                            }
                        }
                        Text(
                            text = it.overview,
                            textAlign = TextAlign.Start,
                            maxLines = DETAIL_TEXT_MAX_LINES,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .align(Alignment.Start)
                        )
                        Text(
                            text = stringResource(R.string.artist_title_text),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.Start)
                        )
                        ActorsListView(state = stateFilmDetail)
                    }) {
                    FilmImageDetails(model = MainActivity.BASE_URL_MOVIES + film.posterPicture)
                }
            }
        }
    }
}

@Composable
fun CategoryDetailsView(filmGenres: MutableList<Genre>) {
    LazyRow(
        contentPadding = PaddingValues(start = 5.dp),
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth(0.75F)
    ) {
        items(items = filmGenres) { category ->
            category.let {
                GenresDetailsChip(item = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenresDetailsChip(item: Genre) {
    Chip(
        onClick = {},
        modifier = Modifier
            .padding(2.dp)
            .height(20.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary),
        colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent)
    ) {

        Text(text = item.name, fontSize = 12.sp)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmImageDetails(model: Any?) {
    GlideImage(
        model = model,
        contentDescription = null,
        alignment = Alignment.TopStart,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    )
}

@Composable
fun ActorsListView(state: State<ViewModelDetailsState>) {
    if (state.value is ViewModelDetailsState.Success) {
        val actors = (state.value as ViewModelDetailsState.Success).actors
        LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
            items(items = actors) { actor ->
                actor.let {
                    ActorListItem(actor = it)
                }
            }
        }
    }
}