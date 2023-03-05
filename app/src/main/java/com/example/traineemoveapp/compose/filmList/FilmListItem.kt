package com.example.traineemoveapp.compose.filmList

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.R
import com.example.traineemoveapp.compose.utlis.FilmImage

@Composable
fun FilmListItem(film: Film, onClick: () -> Unit) {
    ImageListItem(name = film.title, pathImage = film.posterPicture, descriptionText = film.overview, raiting = film.ratings, pg = film.adult, onClick = onClick)
}

@OptIn(ExperimentalMaterialApi::class) @Composable
fun ImageListItem(name: String, pathImage: String, descriptionText: String, raiting: Float, pg: String, onClick: () -> Unit) {
    Card(onClick = onClick, elevation = dimensionResource(id = R.dimen.card_elevation), modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(top = dimensionResource(id = R.dimen.card_bottom_margin), bottom = dimensionResource(id = R.dimen.card_bottom_margin))) {
        Column(Modifier.fillMaxWidth()) {
            FilmImage(model = pathImage, Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.flim_list_item_image_height)), contentScale = ContentScale.Crop)
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Start,  maxLines = 2, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            Text(text = descriptionText, textAlign = TextAlign.Start,  fontSize = 12.sp, maxLines = 8, modifier = Modifier
                    .fillMaxWidth()
                    .padding( vertical = 10.dp, horizontal = 5.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally))
            Row(modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 3.dp, top = 3.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,

            ) {
                RatingBar(rating = raiting, spaceBetween = 3.dp, size = 20.dp, modifier = Modifier)
                AgeRatingComponent(ageRating = pg)
            }
        }
    }
}

@Composable
fun AgeRatingComponent(ageRating: String) {
    Text(
            text = ageRating,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .drawBehind {
                        drawCircle(
                                Color.Black,
                                radius = 40f,
                                style = Stroke(
                                        width = 2f
                                )
                        )
                    }
    )
}