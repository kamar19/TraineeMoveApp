package com.example.traineemoveapp.compose.filmList

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.model.Film
import com.example.traineemoveapp.R
import com.example.traineemoveapp.compose.utlis.FilmImage

@Composable
fun FilmListItem(film: Film, idImage:Int, onClick: () -> Unit) {
    ImageListItem(name = film.name, idImage = idImage, descriptionText = film.description, raiting = film.rating, pg = film.pg, onClick = onClick)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageListItem(name: String, idImage: Int, descriptionText: String, raiting: Float, pg: String, onClick: () -> Unit) {
    Card(
            onClick = onClick,
            elevation = dimensionResource(id = R.dimen.card_elevation),
            modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
                    .padding(top = dimensionResource(id = R.dimen.card_bottom_margin), bottom = dimensionResource(id = R.dimen.card_bottom_margin))
    ) {
        Column(Modifier.fillMaxWidth()
        ) {
            FilmImage(
                    model = idImage,
                    contentDescription = stringResource(R.string.a11y_film_item_image), Modifier
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
            Row(modifier = Modifier
                    .padding(start = 20.dp, bottom = 20.dp))
                {
                RatingBar(rating = raiting, spaceBetween = 3.dp, size = 10.dp)
                    Box( modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    ) {
                        Image(painter = painterResource(R.drawable.circle), contentDescription = "", alignment = Alignment.CenterEnd, contentScale = ContentScale.FillBounds, modifier = Modifier.height(21.dp).width(21.dp))
//                Button(onClick = {}, shape = RoundedCornerShape(percent = 50),
//                        modifier = Modifier
//                        .height(20.dp)
//                        .width(20.dp)
//                        ,
//                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent,),
////                        .padding(horizontal = 16.dp, vertical = 24.dp)
//                )

                        Text(text = pg, modifier = Modifier.size(15.dp))

                    }

            }
        }
    }
}