package nextstep.signup.ui.util

import nextstep.signup.R

class NameValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return when {
            value.length !in MIN_NAME_LENGTH..MAX_NAME_LENGTH -> ValidationResult.Invalid(
                R.string.signup_name_length_error_message,
            )

            !value.matches(USERNAME_REGEX) -> ValidationResult.Invalid(
                R.string.signup_name_invalid_character_error_message,
            )

            else -> ValidationResult.Correct
        }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MAX_NAME_LENGTH = 5

        private val USERNAME_REGEX = "^[a-zA-Z가-힣]+$".toRegex()
    }
}