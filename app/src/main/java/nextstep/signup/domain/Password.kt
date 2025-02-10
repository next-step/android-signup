package nextstep.signup.domain

import nextstep.signup.domain.PasswordValidationResult.VALID
import nextstep.signup.domain.PasswordValidationResult.INVALID_CHARACTER
import nextstep.signup.domain.PasswordValidationResult.INVALID_LENGTH

@JvmInline
value class Password(val value: String) {
    fun validate(): PasswordValidationResult {
        return when {
            value.length !in MINIMUM_LENGTH..MAXIMUM_LENGTH -> INVALID_LENGTH
            !value.matches(passwordRegex) -> INVALID_CHARACTER
            else -> VALID
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 8
        private const val MAXIMUM_LENGTH = 16
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}\$"
        private val passwordRegex = Regex(PASSWORD_REGEX)
    }
}

enum class PasswordValidationResult {
    VALID,
    INVALID_LENGTH,
    INVALID_CHARACTER,
}
