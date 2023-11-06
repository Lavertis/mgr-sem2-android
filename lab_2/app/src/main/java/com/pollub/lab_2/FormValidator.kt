package com.pollub.lab_2

class FormValidator {
    fun validateName(name: String, setFieldError: (String?) -> Unit) {
        when {
            name.isBlank() -> setFieldError("Name cannot be empty")
            else -> setFieldError(null)
        }
    }

    fun validateEmail(email: String, setFieldError: (String?) -> Unit) {
        val emailPattern = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
        when {
            email.isBlank() -> setFieldError("E-mail cannot be empty")
            !emailPattern.matches(email) -> setFieldError("Invalid e-mail address")
            else -> setFieldError(null)
        }
    }

    fun validateColorCount(
        colorCount: String,
        min: Int,
        max: Int,
        setFieldError: (String?) -> Unit
    ) {
        if (min > max) throw IllegalArgumentException("min must be less than or equal to max")
        when (colorCount.toIntOrNull()) {
            null -> setFieldError("Please enter a number")
            !in min..max -> setFieldError("Number of colors must be between 5 and 10")
            else -> setFieldError(null)
        }
    }
}
