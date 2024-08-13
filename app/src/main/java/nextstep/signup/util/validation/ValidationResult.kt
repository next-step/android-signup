package nextstep.signup.util.validation

sealed class ValidationResult{
    data object Pending : ValidationResult()
    data object Success : ValidationResult()
    data class Error (val type: ValidationErrorType) : ValidationResult()
}

enum class ValidationErrorType {
    LengthError, RegexError, EqualityError
}