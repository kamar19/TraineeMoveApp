package com.example.traineemoveapp.compose.utlis

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_IMAGE_HEIGHT
import com.example.traineemoveapp.MainActivity.Companion.DETAIL_IMAGE_WIDTH

@Composable
fun FilmImage(
        model: Any?,
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
    Image(painter = painterResource(id = model as Int), contentDescription = null, alignment = Alignment.TopStart, contentScale = ContentScale.Crop, modifier = Modifier
            .height(DETAIL_IMAGE_HEIGHT.dp)
            .width(DETAIL_IMAGE_WIDTH.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5)))

}