package nextstep.signup.ui.model

data class SignUpInputModel(
    val username: String,
    val email: String,
    val password: String,
    val passwordConfirm: String,
) {
    constructor() : this(
        username = "",
        email = "",
        password = "",
        passwordConfirm = "",
    )
}