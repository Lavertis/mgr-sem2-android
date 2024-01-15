package com.pollub.masterand.screens.profile.form

class ProfileFormValidator {
    var nameError: String? = null
    var emailError: String? = null
    var colorCountError: String? = null

    fun validate(name: String, email: String, colorCount: String): Boolean {
        nameError = validateName(name)
        emailError = validateEmail(email)
        colorCountError = validateColorCount(colorCount)
        return nameError == null && emailError == null && colorCountError == null
    }

    private fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "Name cannot be empty"
            else -> null
        }
    }

    private fun validateEmail(email: String): String? {
        val emailPattern = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
        return when {
            email.isBlank() -> "E-mail cannot be empty"
            !emailPattern.matches(email) -> "Invalid e-mail address"
            else -> null
        }
    }

    private fun validateColorCount(colorCount: String): String? {
        return when (colorCount.toIntOrNull()) {
            null -> "Please enter a number"
            !in 5..10 -> "Number of colors must be between 5 and 10"
            else -> null
        }
    }
}
