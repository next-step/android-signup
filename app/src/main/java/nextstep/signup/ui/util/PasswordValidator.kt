package nextstep.signup.ui.util

import nextstep.signup.R

class PasswordValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return when {
            value.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH -> ValidationResult.Invalid(
                R.string.signup_password_length_error_message,
            )

            !value.matches(PASSWORD_REGEX) -> ValidationResult.Invalid(
                R.string.signup_password_complexity_error_message,
            )

            else -> ValidationResult.Correct
        }
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 16

        private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()
    }
}
