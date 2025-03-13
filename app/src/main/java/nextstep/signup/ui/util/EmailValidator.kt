package nextstep.signup.ui.util

import nextstep.signup.R

object EmailValidator : Validator {

    override fun validate(value: String): ValidationResult {
        return if (!value.matches(EMAIL_REGEX)) {
            ValidationResult.Invalid(R.string.signup_email_format_error_message)
        } else {
            ValidationResult.Correct
        }
    }
}

private val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
