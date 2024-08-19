package nextstep.signup.model

internal data class SignUpUserInfo(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
) {
    val isNotContainBlank: Boolean by lazy {
        listOf(
            nameError,
            emailError,
            passwordError,
            passwordConfirmError
        ).none { it.isBlank }
    }

    val nameError: NameError by lazy {
        when {
            username.isBlank() -> NameError.Blank
            (username.length in 2..5).not() -> NameError.Length
            username.matches(Regex(USERNAME_REGEX)).not() -> NameError.NumberOrSymbol
            else -> NameError.None
        }
    }

    val emailError: EmailError by lazy {
        when {
            email.isBlank() -> EmailError.Blank
            email.matches(Regex(EMAIL_REGEX)).not() -> EmailError.EmailFormat
            else -> EmailError.None
        }
    }

    val passwordError: PasswordError by lazy {
        when {
            password.isBlank() -> PasswordError.Blank
            (password.length in 8..16).not() -> PasswordError.PasswordLength
            password.matches(Regex(PASSWORD_REGEX)).not() -> PasswordError.PasswordFormat
            else -> PasswordError.None
        }
    }

    val passwordConfirmError: PasswordConfirmError by lazy {
        when {
            passwordConfirm.isBlank() -> PasswordConfirmError.Blank
            passwordConfirm != password -> PasswordConfirmError.PasswordEqual
            else -> PasswordConfirmError.None
        }
    }

    val isAllFieldsValid: Boolean by lazy {
        listOf(
            nameError,
            emailError,
            passwordError,
            passwordConfirmError
        ).all { it.isNone }
    }

    companion object {
        const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
    }
}
