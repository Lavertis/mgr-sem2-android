package com.pollub.lab_4.screens.profile.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

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