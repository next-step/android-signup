package nextstep.signup.state

data class UsernameState(
    val username: String = "",
    val isError: Boolean = false,
    val supportingText: String = "",
)
