package nextstep.signup.model

internal data class SignUpUserInfo(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
) {
    fun isNotContainBlank(): Boolean =
        username.isNotBlank() && email.isNotBlank() && password.isNotBlank() && passwordConfirm.isNotBlank()

    val isNamePass: NameError
        get() = when {
            username.isBlank() -> NameError.Bank
            (username.length in 2..5).not() -> NameError.LengthError
            username.matches(Regex(USERNAME_REGEX)).not() -> NameError.NumberOrSymbol
            else -> NameError.None
        }

    val isEmailPass: EmailError
        get() = when {
            email.isBlank() -> EmailError.Blank
            email.matches(Regex(EMAIL_REGEX)).not() -> EmailError.EmailFormat
            else -> EmailError.None
        }

    val isPasswordPass: PasswordError
        get() = when {
            password.isBlank() -> PasswordError.Blank
            (password.length in 8..16).not() -> PasswordError.PasswordLength
            password.matches(Regex(PASSWORD_REGEX)).not() -> PasswordError.PasswordFormat
            else -> PasswordError.None
        }

    val isPasswordConfirmPass: PasswordConfirmError
        get() = when {
            passwordConfirm.isBlank() -> PasswordConfirmError.Blank
            passwordConfirm != password -> PasswordConfirmError.PasswordEqual
            else -> PasswordConfirmError.None
        }

    enum class NameError {
        None,
        Bank,
        LengthError,
        NumberOrSymbol
    }

    enum class EmailError {
        None,
        Blank,
        EmailFormat
    }

    enum class PasswordError {
        None,
        Blank,
        PasswordLength,
        PasswordFormat
    }

    enum class PasswordConfirmError {
        None,
        Blank,
        PasswordEqual,
    }

    companion object {
        const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }
}
