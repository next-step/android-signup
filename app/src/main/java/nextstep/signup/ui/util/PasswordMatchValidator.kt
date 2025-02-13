package nextstep.signup.ui.util

import nextstep.signup.R

object PasswordMatchValidator {
    fun validate(password: String, passwordConfirm: String): ValidationResult {
        return when {
            password != passwordConfirm -> ValidationResult.Invalid(
                R.string.signup_password_mismatch_error_message,
            )

            else -> ValidationResult.Correct
        }
    }
}
