package nextstep.signup.core.validation

import nextstep.signup.R

class PasswordMatchValidator(private val originalPassword: String) : Validator {
    override fun validate(value: String): ValidationResult {
        return if (value == originalPassword) {
            ValidationResult(true)
        } else {
            ValidationResult(false, R.string.signup_password_mismatch_error)
        }
    }
}
