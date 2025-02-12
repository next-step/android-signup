package nextstep.signup.domain

@JvmInline
value class Password(val value: String = "") : InputField {
    override fun validate(): ValidationResult {
        return when {
            value.isEmpty() -> ValidationResult.EmptyValue
            value.length !in MINIMUM_LENGTH..MAXIMUM_LENGTH -> ValidationResult.Password.INVALID_LENGTH
            !value.matches(passwordRegex) -> ValidationResult.Password.INVALID_CHARACTER
            else -> ValidationResult.Valid
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 8
        private const val MAXIMUM_LENGTH = 16
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}\$"
        private val passwordRegex = Regex(PASSWORD_REGEX)
    }
}
