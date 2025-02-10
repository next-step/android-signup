package nextstep.signup.state

data class EmailState(
    val email: String = "",
    val isError: Boolean = false,
    val supportingText: String = "",
)
