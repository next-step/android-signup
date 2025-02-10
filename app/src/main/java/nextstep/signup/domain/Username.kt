package nextstep.signup.domain

@JvmInline
value class Username(val value: String) {
    fun validate(): UsernameValidationResult {
        return when {
            value.length !in MINIMUM_LENGTH..MAXIMUM_LENGTH -> UsernameValidationResult.INVALID_LENGTH
            !value.matches(usernameRegex) -> UsernameValidationResult.INVALID_CHARACTER
            else -> UsernameValidationResult.VALID
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 2
        private const val MAXIMUM_LENGTH = 5
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val usernameRegex = Regex(USERNAME_REGEX)
    }
}

enum class UsernameValidationResult {
    VALID,
    INVALID_LENGTH,
    INVALID_CHARACTER,
}
