package nextstep.signup.ui.util

interface Validator {
    fun validate(value: String): ValidationResult
}

sealed interface ValidationResult {
    data object Correct : ValidationResult
    data class Invalid(val messageRes: Int) : ValidationResult
}