package com.example.traineemoveapp.compose.filmDetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_TEXT_MAX_LINES
import com.example.traineemoveapp.MainActivity.Companion.TITLE_TEXT_MAX_LINES
import com.example.traineemoveapp.compose.utlis.FilmImage
import com.example.traineemoveapp.repository.FilmRepository
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
                modifier = modifier,
        ) {
            FilmImage(model = viewModel.getImage(idFilm) , contentDescription = stringResource(com.example.traineemoveapp.R.string.a11y_film_item_image), Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = com.example.traineemoveapp.R.dimen.flim_list_item_image_height)), contentScale = ContentScale.Crop)
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
