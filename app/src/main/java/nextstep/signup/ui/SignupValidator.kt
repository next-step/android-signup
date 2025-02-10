package nextstep.signup.ui

import android.content.Context
import nextstep.signup.R

object SignupValidator {
    private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    fun validateUsername(username: String): ResultType {
        val isEmpty = username.isEmpty()
        val isValidLength = username.length in 2..5
        val isValidFormat = username.matches(Regex(USERNAME_REGEX))

        return when {
            isEmpty -> ResultType.Empty
            !isValidLength -> ResultType.UsernameLength
            !isValidFormat -> ResultType.UsernameInvalidFormat
            else -> ResultType.Success
        }
    }

    fun validateEmail(email: String): ResultType {
        val isEmpty = email.isEmpty()
        val isValidFormat = email.matches(Regex(EMAIL_REGEX))

        return when {
            isEmpty -> ResultType.Empty
            !isValidFormat -> ResultType.EmailInvalidFormat
            else -> ResultType.Success
        }
    }

    fun validatePassword(password: String): ResultType {
        val isEmpty = password.isEmpty()
        val isValidLength = password.length in 8..16
        val isValidFormat = password.matches(Regex(PASSWORD_REGEX))

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

    fun getErrorMessage(context: Context, result: ResultType): String {
        val usernameLength = context.getString(R.string.signup_error_username_length)
        val usernameInvalidFormat = context.getString(R.string.signup_error_username_invalid_format)
        val emailInvalidFormat = context.getString(R.string.signup_error_email_invalid_format)
        val passwordLength = context.getString(R.string.signup_error_password_length)
        val passwordInvalidFormat = context.getString(R.string.signup_error_password_invalid_format)
        val passwordMismatch = context.getString(R.string.signup_error_password_mismatch)

        return when (result) {
            ResultType.Success -> ""
            ResultType.Empty -> ""
            ResultType.UsernameLength -> usernameLength
            ResultType.UsernameInvalidFormat -> usernameInvalidFormat
            ResultType.EmailInvalidFormat -> emailInvalidFormat
            ResultType.PasswordLength -> passwordLength
            ResultType.PasswordInvalidFormat -> passwordInvalidFormat
            ResultType.PasswordMismatch -> passwordMismatch
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