package nextstep.signup.core.validation

enum class NameValidationResult {
    VALID,
    LENGTH_ERROR,
    CHARACTER_ERROR,
}

class NameValidator {
    fun validate(value: String): NameValidationResult {
        return when {
            value.length !in 2..5 -> NameValidationResult.LENGTH_ERROR
            value.any { it.isDigit() || !it.isLetter() } -> NameValidationResult.CHARACTER_ERROR
            else -> NameValidationResult.VALID
        }
    }
}
