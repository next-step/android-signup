package nextstep.signup.ui.signup

class InputValidator {

    private val emailRegex = Regex(EMAIL_REGEX)

    fun checkUserName(userName: String): UserNameValidationState {
        val isInLength = userName.length in USERNAME_ALLOWED_LENGTH
        val hasNumber = userName.any { it.isDigit() }
        val hasSpecialCharacter = userName.any { !it.isLetterOrDigit()}

        return UserNameValidationState(
            isInLength = isInLength,
            hasNumber = hasNumber,
            hasSpecialCharacter = hasSpecialCharacter,
        )
    }

    fun checkEmail(email: String) = email.matches(emailRegex)

    fun checkPassword(password: String): PasswordValidation {
        val isInLength = password.length in PASSWORD_ALLOWED_LENGTH
        val hasCharacter = password.any { it.isLowerCase() || it.isUpperCase() }
        val hasNumber = password.any { it.isDigit() }

        return PasswordValidation(
            isInLength = isInLength,
            hasCharacter = hasCharacter,
            hasNumber = hasNumber,
        )
    }


    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

        private val USERNAME_ALLOWED_LENGTH = 2..5
        private val PASSWORD_ALLOWED_LENGTH = 8..16
    }
}
