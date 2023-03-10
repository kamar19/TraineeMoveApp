package com.example.traineemoveapp.compose.filmDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.traineemoveapp.R
import com.example.traineemoveapp.model.Actor

@Composable
fun ActorListItem(actor: Actor) {
    Card(
        elevation = dimensionResource(id = R.dimen.card_elevation),
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .height(dimensionResource(id = R.dimen.actor_card_height))
            .width(dimensionResource(id = R.dimen.actor_card_width))
            .padding(
                top = dimensionResource(id = R.dimen.card_bottom_margin),
                bottom = dimensionResource(id = R.dimen.card_bottom_margin)
            )
    ) {
        Column() {
            ActorImage(
                model = actor.picture,
                contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.actor_image_height))
                    .clip(RoundedCornerShape(5)),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter
            )
            Text(
                text = actor.actorName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ActorImage(
        model: Any?,
        contentDescription: String?,
        modifier: Modifier,
        alignment: Alignment,
        contentScale: ContentScale,
) {
    GlideImage(
        model = model,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        modifier = modifier
    )
}