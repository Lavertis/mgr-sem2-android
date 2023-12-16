package com.pollub.lab_4.screens.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pollub.lab_4.R
import com.pollub.lab_4.screens.profile.composables.OutlinedTextFieldWithError
import com.pollub.lab_4.screens.profile.composables.ProfileImageWithPicker
import com.pollub.lab_4.screens.profile.form.ProfileForm
import com.pollub.lab_4.ui.theme.Lab_4Theme


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
            modifier = Modifier.padding(bottom = 48.dp)
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
    Lab_4Theme {
        ProfileScreen(navigateToGameScreen = {})
    }
}