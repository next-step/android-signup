package nextstep.signup.core.validation

import nextstep.signup.R

class EmailValidator : Validator {
    override fun validate(value: String): ValidationResult {
        return if (EMAIL_PATTERN.matches(value)) {
            ValidationResult(true)
        } else {
            ValidationResult(false, R.string.sign_up_invalid_email)
        }
    }

    companion object {
        private val EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$".toRegex()
    }
}
