package nextstep.signup.core.validation

import nextstep.signup.R

class NameValidator : Validator {
    override fun validate(value: String): ValidationResult {
        return when {
            value.length !in 2..5 -> ValidationResult(false, R.string.signup_name_length_error)
            value.any { it.isDigit() || !it.isLetter() } -> ValidationResult(false, R.string.signup_name_character_error)
            else -> ValidationResult(true)
        }
    }
}
