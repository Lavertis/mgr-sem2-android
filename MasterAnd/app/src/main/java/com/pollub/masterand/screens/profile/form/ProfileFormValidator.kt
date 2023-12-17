package com.pollub.masterand.screens.profile.form

class ProfileFormValidator {
    companion object {
        fun validateName(name: String): String? {
            return when {
                name.isBlank() -> "Name cannot be empty"
                else -> null
            }
        }

        fun validateEmail(email: String): String? {
            val emailPattern = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
            return when {
                email.isBlank() -> "E-mail cannot be empty"
                !emailPattern.matches(email) -> "Invalid e-mail address"
                else -> null
            }
        }

        fun validateColorCount(colorCount: String, min: Int, max: Int): String? {
            if (min > max) throw IllegalArgumentException("min must be less than or equal to max")
            return when (colorCount.toIntOrNull()) {
                null -> "Please enter a number"
                !in min..max -> "Number of colors must be between 5 and 10"
                else -> null
            }
        }
    }
}
