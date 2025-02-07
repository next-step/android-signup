package nextstep.signup.domain

@JvmInline
value class Username(val value: String) {
    fun validate(): UsernameValidationResult {
        return when {
            !value.matches(usernameRegex) -> UsernameValidationResult.InvalidCharacter
            value.length !in MINIMUM_LENGTH..MAXIMUM_LENGTH -> UsernameValidationResult.InvalidLength
            else -> UsernameValidationResult.Valid
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
    Valid,
    InvalidLength,
    InvalidCharacter,
}
