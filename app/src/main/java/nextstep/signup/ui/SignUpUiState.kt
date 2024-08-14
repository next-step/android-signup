package nextstep.signup.ui

data class SignUpUiState(
    val username: String,
    val email: String,
    val password: String,
    val passwordConfirm: String,
) {
    companion object {
        val DEFAULT =
            SignUpUiState(
                username = "",
                email = "",
                password = "",
                passwordConfirm = "",
            )
    }
}
