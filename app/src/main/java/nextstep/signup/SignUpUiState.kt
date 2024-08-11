package nextstep.signup

data class SignUpUiState(
    val isUsernameLengthError: Boolean = false,
    val isUsernameFormatError: Boolean = false,
    val isEmailFormatError: Boolean = false,
    val isPasswordValidationError: Boolean = false,
    val isPasswordMismatchError: Boolean = false,
    val isSignUpButtonEnabled: Boolean = false
)
