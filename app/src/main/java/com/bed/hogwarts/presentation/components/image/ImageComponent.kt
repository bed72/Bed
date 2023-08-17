package com.bed.hogwarts.presentation.components.image

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageComponent(
    uri: Uri,
    description: String = "Image",
    modifier: Modifier = Modifier
) {
    Image(
        contentScale = ContentScale.Crop,
        contentDescription = description,
        painter = rememberAsyncImagePainter(uri),
        modifier = modifier
            .size(200.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
    )
}
