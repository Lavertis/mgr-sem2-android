package com.pollub.lab_4.screens.profile.form

import android.net.Uri
import androidx.compose.runtime.mutableStateOf


class ProfileForm {
    val errorText = mutableStateOf(mapOf<String, String?>())

    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val colorCount = mutableStateOf("")
    val profileImageUri = mutableStateOf<Uri?>(null)

    fun validate(): Boolean {
        errorText.value = mapOf(
            "name" to ProfileFormValidator.validateName(name.value),
            "email" to ProfileFormValidator.validateEmail(email.value),
            "colorCount" to ProfileFormValidator.validateColorCount(
                colorCount.value,
                min = 5,
                max = 10
            )
        )
        return errorText.value.values.all { it == null }
    }
}
