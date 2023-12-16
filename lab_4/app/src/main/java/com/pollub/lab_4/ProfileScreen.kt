package com.pollub.lab_4

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pollub.lab_4.ui.theme.Lab_4Theme

@Composable
private fun ProfileImageWithPicker(profileImageUri: Comparable<*>, selectImageOnClick: () -> Unit) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithError(
    state: MutableState<String>,
    errorText: String?,
    label: String?,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val isError = errorText != null
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(label ?: "") },
        singleLine = true,
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        supportingText = { Text(if (isError && errorText != null) errorText else "") },
        trailingIcon = { if (isError) Icon(Icons.Rounded.Warning, contentDescription = null) },
    )
}


@Composable
fun ProfileScreen(onNextButtonClicked: (colorCount: Int) -> Unit) {
    val context = LocalContext.current
    val errorText = rememberSaveable { mutableStateOf(mapOf<String, String?>()) }

    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val colorCount = rememberSaveable { mutableStateOf("") }
    val profileImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    fun setFieldError(fieldName: String, errorMessage: String? = null) {
        errorText.value += (fieldName to errorMessage)
    }

    fun validateFields(): Boolean {
        val formValidator = FormValidator()
        formValidator.validateName(name.value) { setFieldError("name", it) }
        formValidator.validateEmail(email.value) { setFieldError("email", it) }
        formValidator.validateColorCount(
            colorCount.value,
            min = 5,
            max = 10
        ) { setFieldError("colorCount", it) }

        return errorText.value.values.all { it == null }
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->
            if (selectedUri != null) {
                profileImageUri.value = selectedUri
            }
        })

    val verticalSpaceBetweenFields = 12.dp

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
            profileImageUri = profileImageUri.value ?: R.drawable.baseline_question_mark_24
        ) {
            imagePicker.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = name,
            errorText = errorText.value["name"],
            label = "Name",
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = email,
            errorText = errorText.value["email"],
            label = "E-mail",
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = colorCount,
            errorText = errorText.value["colorCount"],
            label = "Number of colors",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {
                val isFormValid = validateFields()
                if (isFormValid || true) { // TODO: remove true
                    Toast.makeText(context, "Form is valid", Toast.LENGTH_SHORT).show()
                    onNextButtonClicked(colorCount.value.toInt())
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Lab_4Theme {
        ProfileScreen(onNextButtonClicked = {})
    }
}