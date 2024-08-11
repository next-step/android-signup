package nextstep.signup.util.validation

object ValidationUtil {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"


    fun userNameValidate(userName : String) : ValidationResult {
        if(userName.isBlank()) return ValidationResult.ValidationPending
        if(userName.length !in 2 .. 6)
            return ValidationResult.ValidationError(ValidationErrorType.LengthError)
        if(!userName.matches(Regex(USERNAME_REGEX)))
            return ValidationResult.ValidationError(ValidationErrorType.RegexError)

        return ValidationResult.ValidationSuccess
    }

    fun emailValidate(userName : String) : ValidationResult {
        if(userName.isBlank()) return ValidationResult.ValidationPending
        if(!userName.matches(Regex(EMAIL_REGEX)))
            return ValidationResult.ValidationError(ValidationErrorType.RegexError)

        return ValidationResult.ValidationSuccess
    }
}