package nextstep.signup

enum class ValidationResult(val resourceId: Int?) {
    SUCCESS(null),
    STRING_EMPTY(null),
    USERNAME_INVALID_LENGTH(R.string.username_length_error),
    USERNAME_INVALID_CHARACTERS(R.string.username_invalid_characters_error),
    EMAIL_INVALID_FORMAT(R.string.email_invalid_format_error),
    PASSWORD_INVALID_LENGTH(R.string.password_length_error),
    PASSWORD_MISSING_REQUIRE_CHARACTERS(R.string.password_missing_require_characters_error),
    PASSWORD_MISMATCH(R.string.password_mismatch_error)
}

object InputValidation {

    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun validateUserName(userName: String): ValidationResult {
        return when {
            userName.isBlank() -> {
                ValidationResult.STRING_EMPTY
            }
            userName.length !in 2..5 -> {
                ValidationResult.USERNAME_INVALID_LENGTH
            }
            !userName.matches(Regex(USERNAME_REGEX)) -> {
                ValidationResult.USERNAME_INVALID_CHARACTERS
            }
            else -> ValidationResult.SUCCESS
        }
    }

    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> {
                ValidationResult.STRING_EMPTY
            }
            !email.matches(Regex(EMAIL_REGEX)) -> {
                ValidationResult.EMAIL_INVALID_FORMAT
            }
            else -> ValidationResult.SUCCESS
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> {
                ValidationResult.STRING_EMPTY
            }
            password.length !in 8..16 -> {
                ValidationResult.PASSWORD_INVALID_LENGTH
            }
            !password.matches(Regex(PASSWORD_REGEX)) -> {
                ValidationResult.PASSWORD_MISSING_REQUIRE_CHARACTERS
            }
            else -> ValidationResult.SUCCESS
        }
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return when {
            password.isBlank() || confirmPassword.isBlank() -> {
                ValidationResult.STRING_EMPTY
            }
            password != confirmPassword -> {
                ValidationResult.PASSWORD_MISMATCH
            }
            else -> ValidationResult.SUCCESS
        }
    }
}