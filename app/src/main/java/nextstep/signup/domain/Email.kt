package nextstep.signup.domain

@JvmInline
value class Email(val value: String) {
    fun validate(): EmailValidationResult {
        return when {
            !value.matches(emailRegex) -> EmailValidationResult.INVALID_FORM
            else -> EmailValidationResult.VALID
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val emailRegex = Regex(EMAIL_REGEX)
    }
}

enum class EmailValidationResult {
    VALID,
    INVALID_FORM,
}