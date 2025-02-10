package nextstep.signup

data class SignUpState(
    val username: String = "",
    val userNameErrorMessage: String = "",
    val email: String = "",
    val emailErrorMessage: String = "",
    val password: String = "",
    val passwordErrorMessage: String = "",
    val passwordConfirm: String = "",
    val passwordConfirmErrorMessage: String = "",
) {
    val isSignUpEnabled
        get() = listOf(
            userNameErrorMessage,
            emailErrorMessage,
            passwordErrorMessage,
            passwordConfirmErrorMessage
        ).all { it.isBlank() } && listOf(
            username,
            email,
            password,
            passwordConfirm
        ).all { it.isNotBlank() }
}

