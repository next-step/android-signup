package nextstep.signup.model

internal data class SignUpUserInfo(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
) {
    fun isNotContainBlank(): Boolean =
        username.isNotBlank() && email.isNotBlank() && password.isNotBlank() && passwordConfirm.isNotBlank()
}
