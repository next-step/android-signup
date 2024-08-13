package nextstep.signup.model

object InputValidator {
    private const val MINIMUM_USERNAME_LENGTH = 2
    private const val MAXIMUM_USERNAME_LENGTH = 5
    private const val MINIMUM_PASSWORD_LENGTH = 8
    private const val MAXIMUM_PASSWORD_LENGTH = 16

    fun isValid(input: String, type: TextFieldType): TextFieldState {
        return when (type) {
            TextFieldType.Username -> isValidUsername(input)
            TextFieldType.Email -> isValidEmail(input)
            TextFieldType.Password -> isValidPassword(input)
            TextFieldType.Default -> TextFieldState.Default
            TextFieldType.PasswordConfirm -> TextFieldState.Default
        }
    }

    private fun isValidUsername(username: String): TextFieldState {
        if (username.isEmpty()) {
            return TextFieldState.Default
        }

        if (username.matches(Regex(ValidationRule.USERNAME_REGEX))) {
            return if (username.length in MINIMUM_USERNAME_LENGTH..MAXIMUM_USERNAME_LENGTH) {
                TextFieldState.Valid
            } else {
                TextFieldState.LengthError
            }
        }
        return TextFieldState.InValid
    }

    private fun isValidEmail(email: String): TextFieldState {
        if (email.isEmpty()) {
            return TextFieldState.Default
        }

        if (email.matches(Regex(ValidationRule.EMAIL_REGEX))) {
            return TextFieldState.Valid
        }
        return TextFieldState.InValid
    }

    private fun isValidPassword(password: String): TextFieldState {
        if (password.isEmpty()) {
            return TextFieldState.Default
        }

        if (password.matches(Regex(ValidationRule.PASSWORD_REGEX))) {
            return if (password.length in MINIMUM_PASSWORD_LENGTH..MAXIMUM_PASSWORD_LENGTH) {
                TextFieldState.Valid
            } else {
                TextFieldState.LengthError
            }
        }
        return TextFieldState.InValid
    }
}