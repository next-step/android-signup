package nextstep.signup.util.validation

object Validator {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"


    fun userNameValidate(userName: String): ValidationResult {
        if (userName.isBlank()) return ValidationResult.Pending
        if (userName.length !in 2..6)
            return ValidationResult.Error(ValidationErrorType.LengthError)
        if (!userName.matches(Regex(USERNAME_REGEX)))
            return ValidationResult.Error(ValidationErrorType.RegexError)

        return ValidationResult.Success
    }

    fun emailValidate(email: String): ValidationResult {
        if (email.isBlank()) return ValidationResult.Pending
        if (!email.matches(Regex(EMAIL_REGEX)))
            return ValidationResult.Error(ValidationErrorType.RegexError)

        return ValidationResult.Success
    }

    fun passwordValidate(password: String): ValidationResult {
        if (password.isBlank()) return ValidationResult.Pending
        if (password.length !in 8..16)
            return ValidationResult.Error(ValidationErrorType.LengthError)
        if (!password.matches(Regex(PASSWORD_REGEX)))
            return ValidationResult.Error(ValidationErrorType.RegexError)

        return ValidationResult.Success
    }

    fun passwordConfirmValidate(
        password: String,
        passwordConfirm: String
    ): ValidationResult {
        if (passwordConfirm.isBlank()) return ValidationResult.Pending
        if (password != passwordConfirm)
            return ValidationResult.Error(ValidationErrorType.EqualityError)

        return ValidationResult.Success
    }
}