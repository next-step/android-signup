package nextstep.signup

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
) {
    private val usernameRegex = "^[a-zA-Z가-힣]+$"
    private val emailREGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private val passwordREGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

    val isUsernameLengthError = username.isNotEmpty() && username.length !in 2..5
    val isUsernameFormatError = username.isNotEmpty() && !username.matches(Regex(usernameRegex))
    val isEmailFormatError = email.isNotEmpty() && !email.matches(Regex(emailREGEX))
    val isPasswordValidationError = password.isNotEmpty() && !password.matches(Regex(passwordREGEX))
    val isPasswordMismatchError = passwordConfirm.isNotEmpty() && passwordConfirm != password

    private val areFieldsFilled =
        username.isNotEmpty() && email.isNotEmpty() &&
        password.isNotEmpty() && passwordConfirm.isNotEmpty()

    private val areFieldsValid =
        !isUsernameLengthError && !isUsernameFormatError &&
        !isEmailFormatError && !isPasswordValidationError &&
        !isPasswordMismatchError

    val isSignUpButtonEnabled = areFieldsFilled && areFieldsValid
}