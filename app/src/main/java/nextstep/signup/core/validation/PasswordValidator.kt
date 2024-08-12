package nextstep.signup.core.validation

enum class PasswordValidationResult {
    VALID,
    INVALID_LENGTH,
    INVALID_COMPLEXITY,
}

class PasswordValidator : Validator<PasswordValidationResult> {

    override fun validate(value: String): PasswordValidationResult {
        return when {
            value.length !in 8..16 -> PasswordValidationResult.INVALID_LENGTH
            !value.any { it.isDigit() } || !value.any { it.isLetter() } -> PasswordValidationResult.INVALID_COMPLEXITY
            else -> PasswordValidationResult.VALID
        }
    }
}
