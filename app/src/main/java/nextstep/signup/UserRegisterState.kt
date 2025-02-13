package nextstep.signup

data class UserRegisterState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
)
