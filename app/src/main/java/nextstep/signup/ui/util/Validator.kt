package nextstep.signup.ui.util

interface Validator {
    fun validate(value: String): ValidationResult
}

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: Int?
)
