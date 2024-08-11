package nextstep.signup.core.validation

import nextstep.signup.R

class PasswordValidator : Validator {
    override fun validate(value: String): ValidationResult {
        return when {
            value.length !in 8..16 -> ValidationResult(false, R.string.signup_password_length_error)
            !value.any { it.isDigit() } || !value.any { it.isLetter() } -> ValidationResult(false, R.string.signup_password_complexity_error)
            else -> ValidationResult(true)
        }
    }
}