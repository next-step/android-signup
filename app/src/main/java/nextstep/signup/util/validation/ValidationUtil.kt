package nextstep.signup.util.validation

object ValidationUtil {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"


    fun userNameValidate(userName : String) : ValidationResult {
        if(userName.isBlank()) return ValidationResult.ValidationPending
        if(userName.length !in 2 .. 6)
            return ValidationResult.ValidationError(ValidationErrorType.LengthError)
        if(!userName.matches(Regex(USERNAME_REGEX)))
            return ValidationResult.ValidationError(ValidationErrorType.RegexError)

        return ValidationResult.ValidationSuccess
    }

    fun emailValidate(email : String) : ValidationResult {
        if(email.isBlank()) return ValidationResult.ValidationPending
        if(!email.matches(Regex(EMAIL_REGEX)))
            return ValidationResult.ValidationError(ValidationErrorType.RegexError)

        return ValidationResult.ValidationSuccess
    }

    fun passwordValidate(password : String) : ValidationResult {
        if(password.isBlank()) return ValidationResult.ValidationPending
        if(password.length !in 8 .. 16)
            return ValidationResult.ValidationError(ValidationErrorType.LengthError)
        if(!password.matches(Regex(PASSWORD_REGEX)))
            return ValidationResult.ValidationError(ValidationErrorType.RegexError)

        return ValidationResult.ValidationSuccess
    }
}