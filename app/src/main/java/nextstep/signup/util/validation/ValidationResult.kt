package nextstep.signup.util.validation

sealed class ValidationResult{
    data object Pending : ValidationResult()
    data object Success : ValidationResult()
    data class Error (val type: ValidationErrorType) : ValidationResult()

    fun getErrorType() : ValidationErrorType? = (this as? Error)?.type

}

enum class ValidationErrorType {
    LengthError, RegexError, EqualityError
}