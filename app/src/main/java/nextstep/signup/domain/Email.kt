package nextstep.signup.domain

@JvmInline
value class Email(val value: String) {
    fun validate(): EmailValidationResult {
        return when {
            !value.matches(emailRegex) -> EmailValidationResult.InvalidForm
            else -> EmailValidationResult.Valid
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val emailRegex = Regex(EMAIL_REGEX)
    }
}

enum class EmailValidationResult {
    Valid,
    InvalidForm,
}