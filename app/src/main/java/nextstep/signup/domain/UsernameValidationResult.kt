package nextstep.signup.domain

enum class UsernameValidationResult {
    SUCCESS,
    INVALID_LENGTH,
    INVALID_FORMAT,
    ;

    companion object {
        private val VALID_RANGE = 2..5
        private const val VALID_REGEX = "^[a-zA-Z가-힣]+$"

        fun match(username: String): UsernameValidationResult = when {
            username.length !in VALID_RANGE -> INVALID_LENGTH
            !VALID_REGEX.toRegex().matches(username) -> INVALID_FORMAT
            else -> SUCCESS
        }
    }
}
