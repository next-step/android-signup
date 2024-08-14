package nextstep.signup.core.validation

enum class EmailValidationResult {
    VALID,
    INVALID_FORMAT,
}

class EmailValidator : Validator<EmailValidationResult> {

    override fun validate(value: String): EmailValidationResult {
        return if (EMAIL_PATTERN.matches(value)) {
            EmailValidationResult.VALID
        } else {
            EmailValidationResult.INVALID_FORMAT
        }
    }

    companion object {
        private val EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$".toRegex()
    }
}
