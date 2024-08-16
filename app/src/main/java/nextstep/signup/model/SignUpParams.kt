package nextstep.signup.model

data class SignUpParams(
    val username: String,
    val email: String,
    val password: String,
    val passwordConfirm: String,
)