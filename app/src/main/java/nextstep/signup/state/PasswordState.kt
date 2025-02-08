package nextstep.signup.state

data class PasswordState(
    val password: String = "",
    val isError: Boolean = false,
    val supportingText: String = "",
)
