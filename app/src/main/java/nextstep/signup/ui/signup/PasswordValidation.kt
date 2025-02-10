package nextstep.signup.ui.signup

data class PasswordValidation(
    val isInLength: Boolean = false,
    val hasCharacter: Boolean = false,
    val hasNumber: Boolean = false,
) {
    val isValidPassword: Boolean
        get() = isInLength && hasCharacter && hasNumber
}
