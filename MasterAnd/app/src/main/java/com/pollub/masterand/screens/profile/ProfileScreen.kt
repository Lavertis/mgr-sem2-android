package com.pollub.masterand.screens.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pollub.masterand.R
import com.pollub.masterand.screens.profile.composables.OutlinedTextFieldWithError
import com.pollub.masterand.screens.profile.composables.ProfileImageWithPicker
import com.pollub.masterand.screens.profile.form.ProfileForm
import com.pollub.masterand.ui.theme.MasterAndTheme


@Composable
fun ProfileScreen(navigateToGameScreen: (colorCount: Int) -> Unit) {
    val verticalSpaceBetweenFields = 12.dp
    val profileForm = remember { ProfileForm() }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->
            if (selectedUri != null) {
                profileForm.profileImageUri.value = selectedUri
            }
        })
    val onNextButtonClicked = {
        if (profileForm.validate() || true) { // TODO: remove true
            navigateToGameScreen(profileForm.colorCount.value.toInt())
        }
    }

    val infiniteScaleAnimation = rememberInfiniteTransition(label = "infiniteScaleAnimation")
    val titleScale by infiniteScaleAnimation.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "titleScale"
    )

    return Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .padding(bottom = 48.dp)
                .graphicsLayer(scaleX = titleScale, scaleY = titleScale)
        )
        ProfileImageWithPicker(
            profileImageUri = profileForm.profileImageUri.value
                ?: R.drawable.baseline_question_mark_24
        ) {
            imagePicker.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = profileForm.name,
            errorText = profileForm.errorText.value["name"],
            label = "Name",
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = profileForm.email,
            errorText = profileForm.errorText.value["email"],
            label = "E-mail",
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = profileForm.colorCount,
            errorText = profileForm.errorText.value["colorCount"],
            label = "Number of colors",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(18.dp))
        Button(onClick = onNextButtonClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MasterAndTheme {
        ProfileScreen(navigateToGameScreen = {})
    }
}