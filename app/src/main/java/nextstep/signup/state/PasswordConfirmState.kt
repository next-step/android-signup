package nextstep.signup.state

data class PasswordConfirmState(
    val passwordConfirm: String = "",
    val isError: Boolean = false,
    val supportingText: String = "",
)
