package nextstep.signup.ui.signin

object SignupValidator {
    private val USERNAME_REGEX = "^[a-zA-Z가-힣]+$".toRegex()
    private val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()

    fun validateUsername(username: String): ResultType {
        val isEmpty = username.isEmpty()
        val isValidLength = username.length in 2..5
        val isValidFormat = username.matches(USERNAME_REGEX)

        return when {
            isEmpty -> ResultType.Empty
            !isValidLength -> ResultType.UsernameLength
            !isValidFormat -> ResultType.UsernameInvalidFormat
            else -> ResultType.Success
        }
    }

    fun validateEmail(email: String): ResultType {
        val isEmpty = email.isEmpty()
        val isValidFormat = email.matches(EMAIL_REGEX)

        return when {
            isEmpty -> ResultType.Empty
            !isValidFormat -> ResultType.EmailInvalidFormat
            else -> ResultType.Success
        }
    }

    fun validatePassword(password: String): ResultType {
        val isEmpty = password.isEmpty()
        val isValidLength = password.length in 8..16
        val isValidFormat = password.matches(PASSWORD_REGEX)

        return when {
            isEmpty -> ResultType.Empty
            !isValidLength -> ResultType.PasswordLength
            !isValidFormat -> ResultType.PasswordInvalidFormat
            else -> ResultType.Success
        }
    }

    fun validatePasswordMatch(
        password: String,
        confirmPassword: String
    ): ResultType {
        val isEmpty = password.isEmpty() || confirmPassword.isEmpty()
        val isMatch = !isEmpty && password == confirmPassword

        return when {
            !isMatch -> ResultType.PasswordMismatch
            else -> ResultType.Success
        }
    }

    sealed class ResultType {
        data object Success : ResultType()
        data object Empty : ResultType()
        data object UsernameLength : ResultType()
        data object UsernameInvalidFormat : ResultType()
        data object EmailInvalidFormat : ResultType()
        data object PasswordLength : ResultType()
        data object PasswordInvalidFormat : ResultType()
        data object PasswordMismatch : ResultType()
    }
}