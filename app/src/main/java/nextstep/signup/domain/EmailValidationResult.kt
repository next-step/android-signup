package nextstep.signup.domain

enum class EmailValidationResult {
    SUCCESS,
    INVALID_FORMAT,
    ;

    companion object {
        private const val VALID_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

        fun match(email: String): EmailValidationResult = when {
            !VALID_REGEX.toRegex().matches(email) -> INVALID_FORMAT
            else -> SUCCESS
        }
    }
}
