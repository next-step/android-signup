package nextstep.signup.domain

@JvmInline
value class Username(val value: String = "") : InputField {
    override fun validate(): ValidationResult {
        return when {
            value.isEmpty() -> ValidationResult.EmptyValue
            value.length !in MINIMUM_LENGTH..MAXIMUM_LENGTH -> ValidationResult.Username.INVALID_LENGTH
            !value.matches(usernameRegex) -> ValidationResult.Username.INVALID_CHARACTER
            else -> ValidationResult.Valid
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 2
        private const val MAXIMUM_LENGTH = 5
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val usernameRegex = Regex(USERNAME_REGEX)
    }
}
