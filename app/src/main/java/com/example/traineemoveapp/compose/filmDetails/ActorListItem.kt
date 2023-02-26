package com.example.traineemoveapp.compose.filmDetails

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.example.traineemoveapp.MainActivity
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Actor
import com.example.traineemoveapp.viewModel.DetailFilmViewModel

@Composable
fun ActorListItem (viewModel:DetailFilmViewModel, actor: Actor) {
    Card(elevation = dimensionResource(id = R.dimen.card_elevation), modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(top = dimensionResource(id = R.dimen.card_bottom_margin), bottom = dimensionResource(id = R.dimen.card_bottom_margin))) {
        Column(Modifier.fillMaxWidth()) {
            ActorImage(model = actor.id?.let { viewModel.getActorImage(it) }, contentDescription = null, Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.flim_list_item_image_height)), contentScale = ContentScale.Crop)
            Text(text = actor.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Start,  maxLines = 2, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun ActorImage(
        model: Any?,
        contentDescription: String?,
        modifier: Modifier = Modifier,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        requestBuilderTransform: RequestBuilderTransform<Drawable> = { it },
) {
    if (LocalInspectionMode.current) {
        Box(modifier = modifier.background(Color.Magenta))
        return
    }
    Image(painter = painterResource(id = model as Int), contentDescription = "", alignment = Alignment.TopStart, contentScale = ContentScale.Crop, modifier = Modifier
            .height(MainActivity.ACTOR_IMAGE_HEIGHT.dp)
            .width(MainActivity.ACTOR_IMAGE_WIDTH.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5)))
}