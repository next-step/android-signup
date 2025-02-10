package nextstep.signup.ui.util

import nextstep.signup.R

class EmailValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return if (!value.matches(Regex(EMAIL_REGEX))) {
            ValidationResult(
                isValid = false,
                errorMessage = R.string.signup_email_format_error_message
            )
        } else {
            ValidationResult(isValid = true, errorMessage = null)
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    }
}
