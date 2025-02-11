package nextstep.signup.textfield

object InputValidator {

    fun validateUsername(username: String): UsernameError {
        return when {
            username.isEmpty() -> UsernameError.NONE
            !username.matches(Regex(USERNAME_REGEX)) -> UsernameError.INVALID_CHARACTER_ERROR
            username.length !in USERNAME_MIN_LENGTH..USERNAME_MAX_LENGTH -> UsernameError.LENGTH_ERROR
            else -> UsernameError.NONE
        }
    }

    fun validatePassword(password: String): PasswordError {
        return when {
            password.isEmpty() -> PasswordError.NONE
            password.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH -> PasswordError.LENGTH_ERROR
            !password.matches(Regex(PASSWORD_REGEX)) -> PasswordError.REQUIRED_CHARACTER_NOT_INCLUDE_ERROR
            else -> PasswordError.NONE
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && email.matches(Regex(EMAIL_REGEX))
    }

    fun isPasswordMatched(password: String, confirmPassword: String): Boolean {
        return password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword
    }

    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    private const val USERNAME_MIN_LENGTH = 2
    private const val USERNAME_MAX_LENGTH = 5
    private const val PASSWORD_MIN_LENGTH = 8
    private const val PASSWORD_MAX_LENGTH = 16

}