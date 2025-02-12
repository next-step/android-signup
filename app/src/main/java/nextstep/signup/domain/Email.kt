package nextstep.signup.domain


@JvmInline
value class Email(val value: String = ""): InputField {
    override fun validate(): ValidationResult {
        return when {
            value.isEmpty() -> ValidationResult.EmptyValue
            !value.matches(emailRegex) -> ValidationResult.Email.INVALID_FORM
            else -> ValidationResult.Valid
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val emailRegex = Regex(EMAIL_REGEX)
    }
}
