package com.pollub.lab_4.screens.profile.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pollub.lab_4.R

@Composable
fun ProfileImageWithPicker(profileImageUri: Comparable<*>, selectImageOnClick: () -> Unit) {
    Box {
        AsyncImage(
            model = profileImageUri,
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.Center),
        )
        IconButton(
            onClick = selectImageOnClick,
            modifier = Modifier
                .offset(x = 32.dp, y = (-32).dp)
                .align(Alignment.TopEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_search_24),
                contentDescription = "Profile photo",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}