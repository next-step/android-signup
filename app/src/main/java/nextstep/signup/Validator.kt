package nextstep.signup

object Validator {
    fun isValid(input: String, type: TextFieldType): TextFieldState {
        return when (type) {
            TextFieldType.Username -> isValidUsername(input)
            TextFieldType.Email -> isValidEmail(input)
            TextFieldType.Password -> isValidPassword(input)
//            TextFieldType.PasswordConfirm -> isValidPassword(input)
            TextFieldType.Default -> TextFieldState.Default
        }
    }

    private fun isValidUsername(username: String): TextFieldState {
        if (username.isEmpty()) {
            return TextFieldState.Default
        }

        if (username.matches(Regex(ValidationRule.USERNAME_REGEX))) {
            return if (username.length in 2..5) {
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
            return if (password.length in 8..16) {
                TextFieldState.Valid
            } else {
                TextFieldState.LengthError
            }
        }
        return TextFieldState.InValid
    }
}