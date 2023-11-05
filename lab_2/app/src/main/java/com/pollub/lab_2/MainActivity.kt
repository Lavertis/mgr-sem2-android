package com.pollub.lab_2

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pollub.lab_2.ui.theme.Lab_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreenInitial()
                }
            }
        }
    }
}

@Composable
private fun ProfileImageWithPicker(profileImageUri: Uri?, selectImageOnClick: () -> Unit) {
    Box {
        AsyncImage(
            model = profileImageUri ?: R.drawable.baseline_question_mark_24,
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
                // wymaga dodania ikony w katalogu /res/drawable
                // (prawy przycisk | New | Vector asset)
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
    isError: Boolean,
    errorText: String?,
    label: String?
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(label ?: "") },
        singleLine = true,
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        supportingText = { Text(if (isError && errorText != null) errorText else "") },
        trailingIcon = { if (isError) Icon(Icons.Rounded.Warning, contentDescription = null) },
    )
}


@Composable
fun ProfileScreenInitial() {
    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val colorCount = rememberSaveable { mutableStateOf("") }
    val profileImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    var isNameError by rememberSaveable { mutableStateOf(false) }
    var isEmailError by rememberSaveable { mutableStateOf(false) }
    var isColorCountError by rememberSaveable { mutableStateOf(false) }

    var nameErrorText by rememberSaveable { mutableStateOf<String?>(null) }
    var emailErrorText by rememberSaveable { mutableStateOf<String?>(null) }
    var colorCountErrorText by rememberSaveable { mutableStateOf<String?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->
            if (selectedUri != null) {
                profileImageUri.value = selectedUri
            }
        })

    fun validateName() {
        var isError = true
        when {
            name.value.isBlank() -> nameErrorText = "Name cannot be empty"
            else -> isError = false
        }
        isNameError = isError
    }

    fun validateEmail() {
        val emailPattern = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
        var isError = true
        when {
            email.value.isBlank() -> emailErrorText = "E-mail cannot be empty"
            !emailPattern.matches(email.value) -> emailErrorText = "Invalid e-mail format"
            else -> isError = false
        }
        isEmailError = isError
    }

    fun validateColorCount() {
        var isError = true
        when (colorCount.value.toIntOrNull()) {
            null -> colorCountErrorText = "Please enter a number"
            !in 5..10 -> colorCountErrorText = "Please enter a number between 5 and 10"
            else -> isError = false
        }
        isColorCountError = isError
    }

    fun validate() {
        validateName()
        validateEmail()
        validateColorCount()
    }

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
            profileImageUri = profileImageUri.value,
            selectImageOnClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = name,
            isError = isNameError,
            errorText = nameErrorText,
            label = "Name"
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = email,
            isError = isEmailError,
            errorText = emailErrorText,
            label = "E-mail"
        )
        Spacer(modifier = Modifier.height(verticalSpaceBetweenFields))
        OutlinedTextFieldWithError(
            state = colorCount,
            isError = isColorCountError,
            errorText = colorCountErrorText,
            label = "Number of colors"
        )
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {
                validate()
                if (!isNameError && !isEmailError && !isColorCountError) {
                    println("Form is valid")
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
fun GreetingPreview() {
    Lab_2Theme {
        ProfileScreenInitial()
    }
}