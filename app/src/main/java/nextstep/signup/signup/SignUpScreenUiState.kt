package nextstep.signup.signup

data class SignUpScreenUiState(
    val username: String,
    val isUserNameLengthOutOfRange: Boolean,
    val isUserNameHasNumberOrSymbol: Boolean,

    val email: String,
    val isInvalidEmail: Boolean,

    val password: String,
    val isPasswordLengthOutOfRange: Boolean,
    val isPasswordHasNotAlphabetAndNumber: Boolean,

    val passwordConfirm: String,
    val isPasswordConfirmNotMatched: Boolean,
) {
    val disableSignUp: Boolean = isUserNameLengthOutOfRange
            || isUserNameHasNumberOrSymbol
            || isInvalidEmail
            || isPasswordLengthOutOfRange
            || isPasswordHasNotAlphabetAndNumber
            || isPasswordConfirmNotMatched

    companion object {
        val Initial: SignUpScreenUiState = SignUpScreenUiState(
            username = "",
            isUserNameLengthOutOfRange = false,
            isUserNameHasNumberOrSymbol = false,
            email = "",
            isInvalidEmail = false,
            password = "",
            isPasswordLengthOutOfRange = false,
            isPasswordHasNotAlphabetAndNumber = false,
            passwordConfirm = "",
            isPasswordConfirmNotMatched = false,
        )
    }
}
