package com.example.traineemoveapp.compose.filmslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.R
import com.example.traineemoveapp.compose.utlis.FilmImage

@Composable
fun FilmListItem(film: Film, onClick: () -> Unit) {
    ImageListItem(name = film.name, imageUrl = film.photo, descriptionText = film.description, onClick = onClick)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageListItem(name: String, imageUrl: String, descriptionText: String, onClick: () -> Unit) {
    Card(
            onClick = onClick,
            elevation = dimensionResource(id = R.dimen.card_elevation),
            modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
                    .padding(top = dimensionResource(id = R.dimen.card_bottom_margin),
                            bottom = dimensionResource(id = R.dimen.card_bottom_margin))
    ) {
        Column(Modifier.fillMaxWidth()
        ) {
            FilmImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.a11y_plant_item_image),
                    Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.plant_item_image_height)),
                    contentScale = ContentScale.Crop
            )
            Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                            .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(
                    text = descriptionText,
                    textAlign = TextAlign.Center,
                    maxLines = 8,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                            .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}