package nextstep.signup.domain

enum class PasswordValidationResult {
    SUCCESS,
    INVALID_LENGTH,
    INVALID_FORMAT,
    ;


    companion object {
        private val VALID_RANGE = 8..16
        private const val VALID_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

        fun match(password: String): PasswordValidationResult = when {
            password.length !in VALID_RANGE -> INVALID_LENGTH
            !VALID_REGEX.toRegex().matches(password) -> INVALID_FORMAT
            else -> SUCCESS
        }
    }
}
