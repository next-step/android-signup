package nextstep.signup.ui.util

import nextstep.signup.R

class PasswordMatchValidator(
    private val enteredPassword: String = "",
) : Validator {

    override fun validate(value: String): ValidationResult {
        return when {
            value != enteredPassword -> ValidationResult(
                isValid = false,
                errorMessage = R.string.signup_password_mismatch_error_message
            )
            else -> ValidationResult(isValid = true, errorMessage = null)
        }
    }
}
