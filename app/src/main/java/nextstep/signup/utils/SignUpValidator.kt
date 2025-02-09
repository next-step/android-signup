package nextstep.signup.utils

enum class UserNameErrorType {
    NO_ERROR,
    USERNAME_LENGTH_ERROR,
    USERNAME_FORMAT_ERROR,
}

enum class PasswordErrorType {
    NO_ERROR,
    PASSWORD_LENGTH_ERROR,
    PASSWORD_FORMAT_ERROR,
}

object SignUpValidator {

    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun validateUserName(userName: String): UserNameErrorType {
        return when {
            userName.isEmpty() -> UserNameErrorType.NO_ERROR
            userName.length < 2 || userName.length > 5 -> UserNameErrorType.USERNAME_LENGTH_ERROR
            !userName.matches(Regex(USERNAME_REGEX)) -> UserNameErrorType.USERNAME_FORMAT_ERROR
            else -> UserNameErrorType.NO_ERROR
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.isEmpty() || email.matches(Regex(EMAIL_REGEX))
    }

    fun validatePassword(password: String): PasswordErrorType {
        return when {
            password.isEmpty() -> PasswordErrorType.NO_ERROR
            password.length < 8 || password.length > 16 -> PasswordErrorType.PASSWORD_LENGTH_ERROR
            !password.matches(Regex(PASSWORD_REGEX)) -> PasswordErrorType.PASSWORD_FORMAT_ERROR
            else -> PasswordErrorType.NO_ERROR
        }
    }

}
