package nextstep.signup.util.validation

sealed class ValidationResult{
    data object ValidationPending : ValidationResult()
    data object ValidationSuccess : ValidationResult()
    data class ValidationError (val type: ValidationErrorType) : ValidationResult()
}

enum class ValidationErrorType {
    LengthError, RegexError, EqualityError
}