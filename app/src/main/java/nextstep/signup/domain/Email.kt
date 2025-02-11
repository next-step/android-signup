package nextstep.signup.domain


@JvmInline
value class Email(val value: String = ""): InputField {
    override fun validate(): ValidationResult {
        return when {
            value.isEmpty() -> ValidationResult.EMPTY_VALUE
            !value.matches(emailRegex) -> ValidationResult.INVALID_FORM
            else -> ValidationResult.VALID
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val emailRegex = Regex(EMAIL_REGEX)
    }
}
